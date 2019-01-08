package mondragon.edu.service;

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

}
