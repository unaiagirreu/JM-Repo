package mondragon.edu.service;

import java.util.List;

import mondragon.edu.clases.Product;

public interface ProductService {
	
	boolean add(Product product);

	List<Product> searchProductsByOrder(int id);

	boolean setStatus(Product product, String string);

	boolean setVehicleId(Product product, Integer id);

}
