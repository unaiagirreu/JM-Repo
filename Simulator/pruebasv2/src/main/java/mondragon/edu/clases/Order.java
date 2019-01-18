package mondragon.edu.clases;

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
@Table(name = "orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Integer id;
	
	@Column(name = "date")
	private String date;
	
/*	@OneToOne
	@JoinColumn(name = "userId")
	private Integer userId;*/
	@Transient
	List<Product> productList;
	
	
	public Order() {}
	
	public Order(int id, List<Product> productList){
		this.id=id;
		this.productList=productList;		
	}

	public List<Product> getProductList() {
		return productList;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

/*	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}*/
	
}
