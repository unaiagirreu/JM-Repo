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

@Repository
public class DaoProductItemMySQL implements DaoProductItem{

	@Autowired
	private SessionFactory sessionFactory;
	
	public boolean add(Product product) {
		System.out.println("aaaaa");
	    sessionFactory.getCurrentSession().save(product);
	    return true;
	}

	@Transactional
	@Override
	public List<Product> seachProductsByOrder(int id) {
		List<Product> productList=new ArrayList<>();
		
		@SuppressWarnings("unchecked")
		TypedQuery<Product> query=sessionFactory.getCurrentSession().createQuery("from Product where order_id='" + id + "'");
		productList=query.getResultList();		
		return productList;
	}

	@Transactional
	@Override
	public boolean setStatus(Product product, String string) {
		@SuppressWarnings("unchecked")
		TypedQuery<Product> query=sessionFactory.getCurrentSession().createQuery("update Product set status = '"+string + "' where id='"+product.getId()+"'");		
		query.executeUpdate();
		return true;
	}

	@Transactional
	@Override
	public boolean setVehicleId(Product product, Integer id) {
		@SuppressWarnings("unchecked")
		TypedQuery<Product> query=sessionFactory.getCurrentSession().createQuery("update Product set vehicle_id = '"+id + "' where id='"+product.getId()+"'");
		query.executeUpdate();
		return true;
	}

	
}
