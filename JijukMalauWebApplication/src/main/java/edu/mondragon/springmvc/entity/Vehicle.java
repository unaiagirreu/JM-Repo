package edu.mondragon.springmvc.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "vehicle")
public class Vehicle implements Runnable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Integer id;
	
	@OneToOne
	@JoinColumn(name = "currentSegment")
	private Segment currentSegment;
	
	@OneToOne
	@JoinColumn(name = "destinationSegment")
	private Segment destinationSegment;
	
	@Column(name = "status")
	private String status;
	
	public Vehicle() {}//aqui se tendra que inicializar tmb con el current segment, pero de momento pa no tener que andar pasando la lista de segments pues no se hace. cuando este la base de datos echa se hace.

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public Segment getCurrentSegment() {
		return currentSegment;
	}
	
	public void setCurrentSegment(Segment currentSegment) {
		this.currentSegment = currentSegment;
	}
	
	public Segment getDestinationSegment() {
		return destinationSegment;
	}
	
	public void setDestinationSegment(Segment destinationSegment) {
		this.destinationSegment = destinationSegment;
	}

	@Override
	public void run() {}
}

