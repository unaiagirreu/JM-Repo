package mondragon.edu.clases;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 * Entity abstract class for Segment
 * 
 * @author unaiagirre
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "segment")
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
	
	/**
	 * A thread will try to adquire the priority
	 * 
	 * @return true if the priority is available
	 */
	public abstract boolean askForPriority();
	
	/**
	 * A thread will try to let the priority
	 * 
	 * @return true if letting priority was succesfull
	 */
	public abstract void letPriority();

	/**
	 * When we dont know if a vehicle is on a parking or workstation and we need the correspondient line we use this function
	 * 
	 * @return the correspondent line
	 */
	public abstract int getLineId();
	

}
