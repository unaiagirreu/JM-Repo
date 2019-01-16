package mondragon.edu.clases;

import java.util.concurrent.Semaphore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

import mondragon.edu.control.ControlVehicles;

@Entity
public class Line extends Segment{
	

	@Column(name = "nextSegment")
	public int nextSegment;
	
	@Column(name = "nextSegment2")
	public int nextSegment2;
	
	@Transient
	public ControlVehicles control;
	
	@Transient
	Semaphore priority;
	
	public Line() {}
	
	public Line(int id, int nextSegment, int nextSegment2, ControlVehicles control) {
		super(id);
		priority=new Semaphore(1);
		this.control=control;
		this.nextSegment=nextSegment;
		this.nextSegment2=nextSegment2;
	}
	

	public int getNextSegment() {
		return nextSegment;
	}

	public void setNextSegment(int nextSegment) {
		this.nextSegment = nextSegment;
	}

	public int getNextSegment2() {
		return nextSegment2;
	}

	public void setNextSegment2(int nextSegment2) {
		this.nextSegment2 = nextSegment2;
	}

	@Override
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
		
	}

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

	@Override
	public void letPriority() {
		priority.release();
	}

	@Override
	public int getLineId() {
		
		return this.getId();
	}

}
