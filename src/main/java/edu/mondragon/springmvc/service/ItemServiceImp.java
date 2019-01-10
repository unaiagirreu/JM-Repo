package edu.mondragon.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.mondragon.springmvc.dao.ItemDao;
import edu.mondragon.springmvc.entity.Item;

@Service
public class ItemServiceImp implements ItemService{
	
	@Autowired
	private ItemDao itemDao;

	@Transactional
	@Override
	public List<Item> getAllItems() {
		return itemDao.getAllItems();
	}

}
