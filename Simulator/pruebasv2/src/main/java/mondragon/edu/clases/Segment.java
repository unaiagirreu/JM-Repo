package mondragon.edu.clases;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "Segment")
public abstract class Segment implements Runnable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Integer id;
	
	@Column(name = "state")
	private String state;
	

	public Segment() {}
	
	public Segment(int id) {
		this.id=id;		
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public Integer getId() {
		return id;
	}

	public abstract void run();
	/*public boolean takePermission(Vehicle vehicle) throws InterruptedException {
		if(sem.availablePermits()==0) return false;
		else {
			sem.acquire();
			if(this.getClass().equals(Line.class))Thread.sleep(2000);
			else if(this.getClass().equals(Workstation.class))Thread.sleep(3000);
			else Thread.sleep(2000);
		}
		return true;
	}*/


	

}
