package edu.mondragon.springmvc.entity;

import java.util.concurrent.Semaphore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;

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
	
	public abstract boolean askForPriority();
	
	public abstract void letPriority();

	public abstract int getLineId();
	

}
