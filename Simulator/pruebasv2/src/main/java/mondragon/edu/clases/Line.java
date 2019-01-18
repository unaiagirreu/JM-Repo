package mondragon.edu.clases;

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
	
	public Line() {}
	
	public Line(int id, int nextSegment, int nextSegment2, ControlVehicles control) {
		super(id);
		// TODO Auto-generated constructor stub
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



}
