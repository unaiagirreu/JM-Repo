package mondragon.edu.clases;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * Entity class for Item
 * 
 * @author kepaurzelai
 * @param id 
 * @param name
 * @param description
 * @param category
 * @param price
 * @param catalog
 * @param img
 */
@Entity
@Table(name = "item")
public class Item {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Integer id;
	
	@Column(name = "name")
	private String name;
		
	@Column(name = "description")
	private String description;
		
	@Column(name = "category")
	private String category;	
	
	@Column(name = "price")
	private Integer price;
	
	@Column(name = "catalog")
	private Integer catalog;
	
	@Column(name = "img")
	private String img;
	
	public Item() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Integer getCatalog() {
		return catalog;
	}

	public void setCatalog(Integer catalog) {
		this.catalog = catalog;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}


	
	
}
