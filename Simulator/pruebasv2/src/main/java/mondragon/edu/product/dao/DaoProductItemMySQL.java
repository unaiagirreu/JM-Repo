package mondragon.edu.product.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

	
}
