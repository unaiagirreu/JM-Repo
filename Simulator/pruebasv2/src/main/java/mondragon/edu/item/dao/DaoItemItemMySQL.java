package mondragon.edu.item.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mondragon.edu.clases.Item;

/**
 * Implements item dao 
 * 
 * @author kepaurzelai
 *
 */
@Repository
public class DaoItemItemMySQL implements DaoItemItem{

	@Autowired
	private SessionFactory sessionFactory;
	
	/**
	 * Adds a Item to the database
	 */
	public boolean add(Item item) {
	    sessionFactory.getCurrentSession().save(item);
	    return true;
	}

	
}
