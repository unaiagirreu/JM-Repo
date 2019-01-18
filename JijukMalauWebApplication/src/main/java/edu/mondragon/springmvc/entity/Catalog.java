package edu.mondragon.springmvc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "catalog")
public class Catalog {
	
	@Id
	private Integer catalogId;
	
	@Column(name="name")
	private String name;
	
	public Catalog() {}
	
	public Catalog(int id, String name) {
		this.catalogId=id;
		this.name=name;
	}
}
