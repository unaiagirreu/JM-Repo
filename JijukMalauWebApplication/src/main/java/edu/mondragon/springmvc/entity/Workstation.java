package edu.mondragon.springmvc.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;


@Entity
public class Workstation extends Segment{
	
	@Column(name = "correspondientLineId")
	public int correspondientLineId;
	
	@Column(name = "name")
	public String name;
	
	@Transient
	List<Product> listaProductos;
	
	/*@Transient
	ControlVehicles controlVehicles;*/
	

	
	public Workstation() {}

	public Workstation(int id,String name, int correspondient/*, ControlVehicles control*/) {
		super(id);
		// TODO Auto-generated constructor stub
		this.correspondientLineId=correspondient;
		this.name=name;
		this.listaProductos=new ArrayList<Product>();
		//this.controlVehicles=control;
	}
	
	
	/*public void addProduct(Product product) {
		this.listaProductos.add(product);
		System.out.println(product.getName()+ " added to the workstation");
	}

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
	
	public void deleteProduct() {
		this.listaProductos.remove(0);
		produce();
	}*/

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
		//produce();	
	}



	


	
	@Override
	public int getLineId() {
		return correspondientLineId;
	}

	@Override
	public boolean askForPriority() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void letPriority() {
		// TODO Auto-generated method stub
		
	}
	
	
}
