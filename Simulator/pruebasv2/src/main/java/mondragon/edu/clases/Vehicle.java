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

/**
 * Entity class for Vehicle and for his movements from segment to segment
 * 
 * @author unaiagirre
 * @param id
 * @param currentSegment
 * @param destinationSegment
 * @param status
 * @param controlVehicles vehicle controller
 * @param moveToParking
 * @param ruta
 * @param context
 * @param vehicleService
 * @param orderService
 * @param productService
 */
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
		this.moveToParking=false;
		this.context=context;
		orderService = context.getBean(OrderService.class);
		productService = context.getBean(ProductService.class);
		vehicleService = context.getBean(VehicleService.class);
	}

	/**
	 * The vehicle moves from segment to segment with the product that has been asigned to it. This function is called once the vehicle
	 * has the product.
	 * 
	 * @param product the product the vehicle is going to move
	 * @throws InterruptedException if the thread is interrupted
	 */
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
	
	/**
	 * This function set a product status as finished and then looks if all the products of the order are finished. If they are, 
	 * sets order status as finished.p
	 * 
	 * @param product the product you want to set as finished
	 */
	private void setProductFinished(Product product) {
		productService.setStatus(product, "finished");
		if(orderService.lookForProductStatus(product.getOrder())) {
			orderService.setStatus(product.getOrder(), "finished");
		}
		
	}
	
	/**
	 * This functions moves a vehicles from where it is to where the product is, to take it and 
	 * to carry it to his destination. He also looks if the vehicle is on a parking or not. If yes, 
	 * he set the parking status free.
	 * 
	 * @param product the product that the vehicle wants to take
	 * @throws InterruptedException if the thread is interrupted
	 */
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
	
	/**
	 * This function moves a vehicle to a parking
	 * 
	 * @param parking the parking is the destination of the vehicle
	 * @throws InterruptedException
	 */
	public void goToParking(Parking parking) throws InterruptedException {
		
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
	
	/**
	 * Lets the priority of the segment when the vehicle arrives to another one
	 * @throws InterruptedException 
	 */
	private void letPriority() throws InterruptedException {
		
		controlVehicles.takeNextLine(this.getCurrentSegment().getId(), false, this);
			
	}
	
	/**
	 * The vehicle tries to take the next segment priority. If the vehicle is aproaching to the workstation 1, he will also ask for 
	 * his priority
	 * @throws InterruptedException 
	 * 	 
	 */
	private void askPriority() throws InterruptedException {
		
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
			
	}
	
	/**
	 * A simple sleep to go from segment to segment
	 * 
	 * @throws InterruptedException if the thread is interrupted
	 */
	private void moveToNextLine() throws InterruptedException {//habria que pedir permisos a las lines
		Thread.sleep(5000);
		
	//	System.out.println("moved from: "+this.currentSegment.getId()+" to: "+this.ruta.get(0));		
	}

	/**
	 * A simple function to set which is the product the vehicle is going to move
	 * 
	 * @param product the product the vehicle is going to move
	 */
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
	
	/**
	 * Keeps waiting to someone to notify him. If the moveToParking is activated, once they notify him he goes to a parking.
	 * 
	 * @throws InterruptedException if the thread is interrupted
	 */
	private void waiting() throws InterruptedException {
		synchronized(this) {
			this.wait();
		}
		if(moveToParking) moveToTheParking();
		else move(this.productToMove);//aqui una variable para que haga el move en caso de que haya un producto y si no sera que tieen que ir a un parking
	}
	
	/**
	 * This function is called when we want move a vehicle to a parking. We ask for a route and then we move the vehicle
	 * 
	 * @throws InterruptedException if the thread is interrupted
	 */
	private void moveToTheParking() throws InterruptedException {
		
		goToParking(controlVehicles.definirRutaParking(this));
	}

	/**
	 * This function sets the vehicle next route
	 * 
	 * @param ruta the route we want to set
	 */
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
