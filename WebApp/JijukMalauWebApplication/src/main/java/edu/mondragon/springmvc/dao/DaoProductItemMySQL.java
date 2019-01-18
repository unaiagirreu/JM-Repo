package edu.mondragon.springmvc.dao;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.mondragon.springmvc.entity.Product;

@Repository
public class DaoProductItemMySQL implements DaoProductItem{

	@Autowired
	private SessionFactory sessionFactory;
	
	public boolean add(Product product) {
	    sessionFactory.getCurrentSession().save(product);
	    return true;
	}

	@Override
	public List<Product> getOrderProducts(int id) {
		TypedQuery<Product> query=sessionFactory.getCurrentSession().createQuery("from Product where order_id='"+id+"'");
		return query.getResultList();
	}

	@Override
	public Long getTotalPrice(Integer id) {
		TypedQuery<Long> query=sessionFactory.getCurrentSession().createQuery("select sum(price) from Product where order_id='"+id+"'");
		return query.getSingleResult();
	}
	
	@Override
	public List<Product> getAllProducts() {
		@SuppressWarnings("unchecked")
	    TypedQuery<Product> query=sessionFactory.getCurrentSession().createQuery("from Product");
	    return query.getResultList();
	}

	@Override
	public BigInteger getProductsQuantityPerCategoryScreen() {
		@SuppressWarnings("unchecked")
	    TypedQuery<BigInteger> query=sessionFactory.getCurrentSession().createNativeQuery("select count(*) as Num_Screens from product where (select date from orders where orders.id = product.order_id) like '%-12-2018' and name = 'Screen';");
	    return query.getSingleResult();		
	}
	@Override
	public BigInteger getProductsQuantityPerCategoryComputers() {
		@SuppressWarnings("unchecked")
		TypedQuery<BigInteger> query=sessionFactory.getCurrentSession().createNativeQuery("select count(*) as Num_Computers from product where (select date from orders where orders.id = product.order_id) like '%-12-2018' and (name = 'Desktop Computer' or name = 'Workstation');");
		return query.getSingleResult();
	}
	@Override
	public BigInteger getProductsQuantityPerCategoryLaptops() {
		@SuppressWarnings("unchecked")
	    TypedQuery<BigInteger> query=sessionFactory.getCurrentSession().createNativeQuery("select count(*) as Num_Laptops from product where (select date from orders where orders.id = product.order_id) like '%-12-2018' and (name = 'Laptop 17' or name = 'Touchscreen Laptop');");
	    return query.getSingleResult();		
	}
	@Override
	public BigInteger getProductsQuantityPerCategoryPrinters() {
		@SuppressWarnings("unchecked")
	    TypedQuery<BigInteger> query=sessionFactory.getCurrentSession().createNativeQuery("select count(*) as Num_Printers from product where (select date from orders where orders.id = product.order_id) like '%-12-2018' and (name = 'Epson Printer' or name = 'Canon Printer');");
	    return query.getSingleResult();		
	}
	@Override
	public BigInteger getProductsQuantityPerCategoryPeriferals() {
		@SuppressWarnings("unchecked")
	    TypedQuery<BigInteger> query=sessionFactory.getCurrentSession().createNativeQuery("select count(*) as Num_Preipherals from product where (select date from orders where orders.id = product.order_id) like '%-12-2018' and (name = 'Keyboard' or name = 'Mouse');");
	    return query.getSingleResult();		
	}

	
}
