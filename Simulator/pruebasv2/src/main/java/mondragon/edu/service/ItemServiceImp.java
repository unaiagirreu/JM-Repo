package mondragon.edu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mondragon.edu.clases.Item;
import mondragon.edu.item.dao.DaoItemItem;

@Service
public class ItemServiceImp implements ItemService{
	
	@Autowired
	private DaoItemItem itemDao;

	@Transactional
	public boolean add(Item item) {
		return itemDao.add(item);
	}

}

