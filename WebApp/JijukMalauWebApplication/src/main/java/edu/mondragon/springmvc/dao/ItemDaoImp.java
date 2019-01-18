package edu.mondragon.springmvc.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.mondragon.springmvc.entity.Item;

@Repository
public class ItemDaoImp implements ItemDao{
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Item> getAllItems() {
		@SuppressWarnings("unchecked")
	    TypedQuery<Item> query=sessionFactory.getCurrentSession().createQuery("from Item");
	    return query.getResultList();
	}

}
