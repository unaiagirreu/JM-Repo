package mondragon.edu.product.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mondragon.edu.clases.Order;
import mondragon.edu.clases.Product;

/**
 * Implemets product dao
 * 
 * @author unaiagirre
 *
 */
@Repository
public class DaoProductItemMySQL implements DaoProductItem{

	@Autowired
	private SessionFactory sessionFactory;
	
	/**
	 * Adds a product to the database
	 * 
	 * @param product the product we add to the database
	 */
	public boolean add(Product product) {
		System.out.println("aaaaa");
	    sessionFactory.getCurrentSession().save(product);
	    return true;
	}

	/**
	 * This function searhcs all the products of a order
	 * 
	 * @return returns a list of products
	 */
	@Transactional
	@Override
	public List<Product> seachProductsByOrder(int id) {
		List<Product> productList=new ArrayList<>();
		
		@SuppressWarnings("unchecked")
		TypedQuery<Product> query=sessionFactory.getCurrentSession().createQuery("from Product where order_id='" + id + "'");
		productList=query.getResultList();		
		return productList;
	}

	/**
	 * This function changes the status of a product
	 * 
	 * @param product the product we want to change
	 * @param status the status we want to set
	 */
	@Transactional
	@Override
	public boolean setStatus(Product product, String string) {
		@SuppressWarnings("unchecked")
		TypedQuery<Product> query=sessionFactory.getCurrentSession().createQuery("update Product set status = '"+string + "' where id='"+product.getId()+"'");		
		query.executeUpdate();
		return true;
	}

	/**
	 * Set a vehicle id to a product
	 * 
	 * @param product the product we want to assign the vehicle id
	 * @param id the vehicle id
	 */
	@Transactional
	@Override
	public boolean setVehicleId(Product product, Integer id) {
		@SuppressWarnings("unchecked")
		TypedQuery<Product> query=sessionFactory.getCurrentSession().createQuery("update Product set vehicle_id = '"+id + "' where id='"+product.getId()+"'");
		query.executeUpdate();
		return true;
	}

	
}
