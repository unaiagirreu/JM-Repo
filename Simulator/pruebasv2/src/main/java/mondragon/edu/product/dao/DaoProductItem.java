package mondragon.edu.product.dao;

import java.util.List;

import mondragon.edu.clases.Product;

public interface DaoProductItem {

	boolean add(Product product);

	List<Product> seachProductsByOrder(int id);

	boolean setStatus(Product product, String string);

	boolean setVehicleId(Product product, Integer id);

}
