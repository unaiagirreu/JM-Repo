package mondragon.edu.clases;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Entity class for Product
 * 
 * @author jonfernandez
 * @param id
 * @param price
 * @param description
 * @param status
 * @param segmentOrigin
 * @param segmentDestination
 * @param vehicle
 * @param order
 * @param time
 * @param name
 */
@Entity
@Table(name = "product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Integer id;
		
	@Column(name = "price")
	private Integer price;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "status")
	private String status;
	
	@OneToOne(targetEntity = Segment.class)
	//@JoinColumn(name = "origin")
	private Workstation segmentOrigin;
	
	@OneToOne(targetEntity = Segment.class)
	//@JoinColumn(name = "destination")
	private Workstation segmentDestination;
	
	/*@OneToOne
	@JoinColumn(name = "userId")
	private Integer userId;*/
	
	@OneToOne(targetEntity = Vehicle.class)
//	@JoinColumn(name = "vehicleId")
	private Vehicle vehicle;
	
	@ManyToOne(targetEntity = Order.class)
//	@JoinColumn(name = "orderId")
	private Order order;
	
	@ManyToOne(targetEntity = Item.class)
//	@JoinColumn(name = "orderId")
	private Item item;
	
	@Column(name = "time")
	private int time;
	
	@Column(name = "name")
	private String name;
	
	/**
	 * @class The class for products, it is formed by an identificator(ID),
	 * an origin segment from which it comes ,a destination segment where it has to go to be shipped
	 * A name to descript the product itself and the time that takes to the workstation to make that product.
	 * */
	public Product() {}
	
	public Product(int id,String name,Workstation origen, Workstation destini, int time) {
		this.id=id;
		this.time=time;
		this.name=name;
		this.segmentOrigin=origen;
		this.segmentDestination=destini;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public String getName() {
		return name;
	}

	public Workstation getSegmentOrigin() {
		return segmentOrigin;
	}

	public void setSegmentOrigin(Workstation segmentOrigin) {
		this.segmentOrigin = segmentOrigin;
	}

	public Workstation getSegmentDestination() {
		return segmentDestination;
	}

	public void setSegmentDestination(Workstation segmentDestination) {
		this.segmentDestination = segmentDestination;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public void setName(String name) {
		this.name = name;
	}	
	/**
	 * 
	 * 
	 */

}
