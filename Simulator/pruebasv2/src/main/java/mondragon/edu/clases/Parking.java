package mondragon.edu.clases;

import java.util.concurrent.Semaphore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

import mondragon.edu.control.ControlVehicles;

/**
 * Entity class for Segment. Extends from Segment.
 * 
 * @author unaiagirre
 * @param correspondientLineId the line below
 * @param name
 * @param control
 * @param priority
 */
@Entity
public class Parking extends Segment{
	
	@Column(name = "correspondientLineId")
	public int correspondientLineId;
	
	@Column(name = "name")
	public String name;
	
	@Transient
	public ControlVehicles control;
	
	@Transient
	Semaphore priority;
	
	public Parking() {}

	public Parking(int id,String name, int correspondient, ControlVehicles control) {
		super(id);
		// TODO Auto-generated constructor stub
		this.priority=new Semaphore(1);
		this.control=control;
		this.name=name;
		this.correspondientLineId=correspondient;
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


	public void setName(String name) {
		this.name = name;
	}


	/*
	 * (non-Javadoc)
	 * @see mondragon.edu.clases.Segment#askForPriority()
	 */
	@Override
	public boolean askForPriority() throws InterruptedException {

		priority.acquire();
		
		return true;	
	}
	/*
	 * (non-Javadoc)
	 * @see mondragon.edu.clases.Segment#letPriority()
	 */
	@Override
	public void letPriority() {
		priority.release();
	}
	/*
	 * (non-Javadoc)
	 * @see mondragon.edu.clases.Segment#getLineId()
	 */
	@Override
	public int getLineId() {
		return correspondientLineId;
	}

}
