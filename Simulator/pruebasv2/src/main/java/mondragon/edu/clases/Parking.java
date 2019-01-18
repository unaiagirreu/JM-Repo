package mondragon.edu.clases;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

import mondragon.edu.control.ControlVehicles;


@Entity
public class Parking extends Segment{
	
	@Column(name = "correspondientLineId")
	public int correspondientLineId;
	
	@Column(name = "name")
	public String name;
	
	@Transient
	public ControlVehicles control;
	
	public Parking() {}

	public Parking(int id,String name, int correspondient, ControlVehicles control) {
		super(id);
		// TODO Auto-generated constructor stub
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



	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
