package edu.mondragon.springmvc.service;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.mondragon.springmvc.dao.DaoProductItem;
import edu.mondragon.springmvc.entity.Product;

@Service
public class ProductServiceImp implements ProductService{

	@Autowired
	private DaoProductItem productDao;
	
	@Transactional
	public boolean add(Product product) {
		return productDao.add(product);
	}

	@Transactional
	@Override
	public List<Product> getOrderProducts(int id) {
		return productDao.getOrderProducts(id);
	}

	@Transactional
	@Override
	public Long getTotalPrice(Integer id) {
		return productDao.getTotalPrice(id);
	}
	
	@Transactional
	public List<Product> getAllProducts() {
		return productDao.getAllProducts();
	}
	@Transactional
	public BigInteger getProductsQuantityPerCategoryScreen() {
		return productDao.getProductsQuantityPerCategoryScreen();
	}
	
	@Transactional
	public BigInteger getProductsQuantityPerCategoryPrinters() {
		return productDao.getProductsQuantityPerCategoryPrinters();
	}
	@Transactional
	public BigInteger getProductsQuantityPerCategoryLaptops() {
		return productDao.getProductsQuantityPerCategoryLaptops();
	}
	@Transactional
	public BigInteger getProductsQuantityPerCategoryComputers(){
		return productDao.getProductsQuantityPerCategoryComputers();
	}
	@Transactional
	public BigInteger getProductsQuantityPerCategoryPeriferals() {
		return productDao.getProductsQuantityPerCategoryPeriferals();
	}


}
