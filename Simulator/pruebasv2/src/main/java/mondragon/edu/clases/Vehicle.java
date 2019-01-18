package mondragon.edu.clases;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import mondragon.edu.control.ControlVehicles;

@Entity
@Table(name = "vehicle")
public class Vehicle implements Runnable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Integer id;
	
	@OneToOne
	@JoinColumn(name = "currentSegment")
	private Segment currentSegment;
	
	@OneToOne
	@JoinColumn(name = "destinationSegment")
	private Segment destinationSegment;
	
	@Column(name = "status")
	private String status;
	
	@Transient
	ControlVehicles controlVehicles;
	
	@Transient
	boolean waiting;
	
	@Transient
	Product productToMove;
	
	@Transient
	List<Integer> ruta;
	
	public Vehicle() {}
	public Vehicle(int id, ControlVehicles control) {//aqui se tendra que inicializar tmb con el current segment, pero de momento pa no tener que andar pasando la lista de segments pues no se hace. cuando este la base de datos echa se hace.
		this.id=id;
		controlVehicles=control;
		this.waiting=true;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void move(Product product) throws InterruptedException {
		int dim=this.ruta.size();
		System.out.println("El vehiculo=" + this.id + " ha pillau el producto=" + product.getName());
		System.out.println(ruta);
		for(int i=0;i<dim-1;i++){
			System.out.println(this.productToMove.getName()+" " +this.ruta.get(0));
			moveToNextLine();
		}
		System.out.println("Se ha entregado el producto. El vehiculo vuelve a estar disponible.");
		controlVehicles.addVehicle(this);
		waiting();
	}
	
	private void moveToNextLine() throws InterruptedException {//habria que pedir permisos a las lines
		Thread.sleep(1000);
		System.out.println("moved from: "+this.currentSegment.getId()+" to: "+this.ruta.get(0) );
		controlVehicles.setNextSegment(this.ruta.get(0), this);
		this.ruta.remove(0);
	}

	public void moveProduct(Product product) {
		this.productToMove = product;
	}

	public void run() {
		try {
			waiting();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void waiting() throws InterruptedException {
		synchronized(this) {
			this.wait();
		}		
		move(this.productToMove);
	}
	
	public void setRuta(List<Integer> ruta) {
		this.ruta=ruta;
	}
	
	public Segment getCurrentSegment() {
		return currentSegment;
	}
	
	public void setCurrentSegment(Segment currentSegment) {
		this.currentSegment = currentSegment;
	}
	
	public Segment getDestinationSegment() {
		return destinationSegment;
	}
	
	public void setDestinationSegment(Segment destinationSegment) {
		this.destinationSegment = destinationSegment;
	}
	

}
