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

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import mondragon.edu.control.ControlVehicles;
import mondragon.edu.service.OrderService;
import mondragon.edu.service.ProductService;
import mondragon.edu.service.VehicleService;

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
	boolean moveToParking;
	
	@Transient
	Product productToMove;
	
	@Transient
	List<Integer> ruta;
	
	@Transient
	AnnotationConfigApplicationContext context;
	
	@Transient
	ProductService productService;
	
	@Transient
	OrderService orderService;
	
	@Transient
	VehicleService vehicleService;
	
	public Vehicle() {}
	public Vehicle(int id, ControlVehicles control,AnnotationConfigApplicationContext context) {//aqui se tendra que inicializar tmb con el current segment, pero de momento pa no tener que andar pasando la lista de segments pues no se hace. cuando este la base de datos echa se hace.
		this.id=id;
		controlVehicles=control;
		this.waiting=true;
		this.moveToParking=false;
		this.context=context;
		orderService = context.getBean(OrderService.class);
		productService = context.getBean(ProductService.class);
		vehicleService = context.getBean(VehicleService.class);
	}

	public void move(Product product) throws InterruptedException {
		int dim=this.ruta.size();
		System.out.println("El vehiculo=" + this.id + " ha pillau el producto=" + product.getName());
		System.out.println(ruta);
		this.status="moving";
		vehicleService.setStatus(this, "moving");
		for(int i=0;i<dim;i++){
			askPriority();
	//		System.out.println(this.productToMove.getName()+" " +this.ruta.get(0));
			moveToNextLine();
			letPriority();
			controlVehicles.setNextSegment(this.ruta.get(0), this);
			vehicleService.setCurrentSegment(this,this.currentSegment);
			this.ruta.remove(0);
		}
		System.out.println("Se ha entregado el producto. El vehiculo vuelve a estar disponible.");
		this.status="stoped";
		vehicleService.setStatus(this, "stoped");
		setProductFinished(product);
		Workstation w=(Workstation) this.getCurrentSegment();
		controlVehicles.addVehicle(this);
		waiting();
	}
	
	private void setProductFinished(Product product) {
		productService.setStatus(product, "finished");
		if(orderService.lookForProductStatus(product.getOrder())) {
			orderService.setStatus(product.getOrder(), "finished");
		}
		
	}
	public void recogerProducto(Product product) throws InterruptedException {
		if((this.currentSegment.getId()>22)&&(this.currentSegment.getId()<27)) {
			Parking parking=(Parking) this.getCurrentSegment();
			parking.setState("free");
		}
		int dim=this.ruta.size();
		System.out.println("El vehiculo=" + this.id + " en busca de=" + product.getName());
		System.out.println(ruta);
		vehicleService.setStatus(this, "moving");
		for(int i=0;i<dim;i++){
			askPriority();
	//		System.out.println(this.productToMove.getName()+" " +this.ruta.get(0));
			moveToNextLine();
			letPriority();
			controlVehicles.setNextSegment(this.ruta.get(0), this);
			vehicleService.setCurrentSegment(this,this.getCurrentSegment());
			this.ruta.remove(0);
		}
		productService.setVehicleId(product, this.id+1);
	}
	
	private void goToParking(Parking parking) throws InterruptedException {
		
		this.ruta.add(parking.getId());
		int dim=this.ruta.size();
		System.out.println("El vehiculo=" + this.id + " se va al parking= " + parking.getId());
		System.out.println(ruta);
		vehicleService.setStatus(this, "movingToParking");
		for(int i=0;i<dim;i++){
			askPriority();
	//		System.out.println(this.productToMove.getName()+" " +this.ruta.get(0));
			moveToNextLine();
			letPriority();
			controlVehicles.setNextSegment(this.ruta.get(0), this);
			vehicleService.setCurrentSegment(this,this.currentSegment);
			this.ruta.remove(0);
		}
		System.out.println("El vehiculo=" + this.id + " ha llegado al parking= " + parking.getId());
		vehicleService.setStatus(this, "stoped");
		controlVehicles.addVehicle(this);
		this.moveToParking=false;
		waiting();
	}
	
	private void letPriority() {
		try {
			controlVehicles.takeNextLine(this.getCurrentSegment().getId(), false, this);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void askPriority() {
		try {
	//		if(this.id==1)System.out.println("hola" + ruta.get(0));
			if(this.ruta.size()==2) {
				if(this.ruta.get(1)==17) {
					controlVehicles.askForWorkstation();					
					controlVehicles.takeNextLine(this.ruta.get(1), true, this);		
				}
				
			}
			if(!this.ruta.get(0).equals(17)) {				
				controlVehicles.takeNextLine(this.ruta.get(0), true, this);	
			}
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void moveToNextLine() throws InterruptedException {//habria que pedir permisos a las lines
		Thread.sleep(1000);
		
	//	System.out.println("moved from: "+this.currentSegment.getId()+" to: "+this.ruta.get(0));		
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
		if(moveToParking) moveToTheParking();
		else move(this.productToMove);//aqui una variable para que haga el move en caso de que haya un producto y si no sera que tieen que ir a un parking
	}
	
	private void moveToTheParking() throws InterruptedException {
		
		goToParking(controlVehicles.definirRutaParking(this));
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
	public void moveToParking() {
		moveToParking=true;		
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

	public ProductService getProductService() {
		return productService;
	}
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	public VehicleService getVehicleService() {
		return vehicleService;
	}
	public void setVehicleService(VehicleService vehicleService) {
		this.vehicleService = vehicleService;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<Integer> getRuta() {
		return ruta;
	}
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}


}
