package mondragon.edu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mondragon.edu.clases.Product;
import mondragon.edu.product.dao.DaoProductItem;

@Service
public class ProductServiceImp implements ProductService{

	@Autowired
	private DaoProductItem productDao;
	
	@Transactional
	public boolean add(Product product) {
		return productDao.add(product);
	}

	@Override
	public List<Product> searchProductsByOrder(int id) {
		// TODO Auto-generated method stub
		return productDao.seachProductsByOrder(id);
	}

	@Override
	public boolean setStatus(Product product, String string) {
		// TODO Auto-generated method stub
		return productDao.setStatus(product, string);
	}

	@Override
	public boolean setVehicleId(Product product, Integer id) {
		// TODO Auto-generated method stub
		return productDao.setVehicleId(product,id);
	}

}
