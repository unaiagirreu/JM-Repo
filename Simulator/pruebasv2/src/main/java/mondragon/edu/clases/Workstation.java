package mondragon.edu.clases;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

import mondragon.edu.control.ControlVehicles;

/**
 * Entity class for Workstation. Extends from Segment.
 * 
 * @author unaiagirre
 */
@Entity
public class Workstation extends Segment{
	
	@Column(name = "correspondientLineId")
	public int correspondientLineId;
	
	@Column(name = "name")
	public String name;
	
	@Transient
	List<Product> listaProductos;
	
	@Transient
	boolean productTaken;	
	
	@Transient
	ControlVehicles controlVehicles;
	
	@Transient
	Semaphore priority;

	
	public Workstation() {}

	public Workstation(int id,String name, int correspondient, ControlVehicles control) {
		super(id);
		// TODO Auto-generated constructor stub
		this.priority=new Semaphore(1);
		this.correspondientLineId=correspondient;
		this.name=name;
		this.listaProductos=new ArrayList<Product>();
		this.controlVehicles=control;
		productTaken=false;
	}
	
	/**
	 * This function adds a product to the workstation queue. 
	 * 
	 * @param product the product adding to the workstation
	 */
	public void addProduct(Product product) {
		this.listaProductos.add(product);
		System.out.println(product.getName()+ " added to the workstation");
	}

	/**
	 * This function generates products by sleeping during the specific time the product has.
	 * 
	 * @return true if the product is well generated
	 */
	public boolean makeProduct() {

		try {
			Thread.sleep(this.listaProductos.get(0).getTime()*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(this.listaProductos.get(0).getName()+ " its ready!");
		
		return true;

	}
	
	/**
	 * While they are products in the product queue, this function keeps generating this products. After
	 * a product is generated, it calls to a vehicle and once a vehicle comes and takes the product, it 
	 * delete the product.
	 */
	public void produce() {
		if(listaProductos.size()==0) {
			try {
				synchronized(this) {
				System.out.println(this.name+" waiting");
				wait();
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		makeProduct();	
		try {
			controlVehicles.callVehicle(listaProductos.get(0));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.productTaken=false;
		deleteProduct();
	}
	
	/**
	 * This function deletes the product from the list
	 */
	public void deleteProduct() {
		this.listaProductos.remove(0);
		produce();
	}

	public int getCorrespondientLineId() {
		return correspondientLineId;
	}

	public void setCorrespondientLineId(int correspondientLineId) {
		this.correspondientLineId = correspondientLineId;
	}

	public String getName() {
		return name;
	}

	public List<Product> getListaProductos() {
		return listaProductos;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		produce();	
	}

	public void setProductTaken(boolean productTaken) {
		this.productTaken = productTaken;
	}

	/**
	 * @see mondragon.edu.clases.Segment#askForPriority()
	 */
	@Override
	public boolean askForPriority() {
		try {
			priority.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;	
	}
	
	/**
	 * This function is used to take the first workstation priority when you are aproaching to it. If we get the priority of the 
	 * line before the workstation the vehicle in the workstation cant go out so when we aproaching to the workstation
	 * we ask for his priority.
	 * 
	 * @return true if the priority is available
	 */
	public boolean askForPriority17() {
		boolean bool;
		bool=priority.tryAcquire();
		
		return bool;	
	}

	/**
	 * @see mondragon.edu.clases.Segment#letPriority()
	 */
	@Override
	public void letPriority() {
		priority.release();
	}
	/**
	 * @see mondragon.edu.clases.Segment#getLineId()
	 */
	@Override
	public int getLineId() {
		return correspondientLineId;
	}
	
	public int getPermits() {
		return priority.availablePermits();
	}
	
}
