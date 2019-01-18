package edu.mondragon.springmvc.dao;

import java.math.BigInteger;
import java.util.List;

import edu.mondragon.springmvc.entity.Product;

public interface DaoProductItem {

	boolean add(Product product);
	List<Product> getOrderProducts(int id);
	Long getTotalPrice(Integer id);
	List<Product> getAllProducts();
	BigInteger getProductsQuantityPerCategoryScreen();
	BigInteger getProductsQuantityPerCategoryPrinters();
	BigInteger getProductsQuantityPerCategoryLaptops();
	BigInteger getProductsQuantityPerCategoryComputers();
	BigInteger getProductsQuantityPerCategoryPeriferals();

}
