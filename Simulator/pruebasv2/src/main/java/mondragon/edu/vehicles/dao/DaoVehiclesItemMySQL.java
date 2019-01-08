package mondragon.edu.vehicles.dao;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mondragon.edu.clases.Vehicle;

@Repository
public class DaoVehiclesItemMySQL implements DaoVehiclesItem{

	@Autowired
	private SessionFactory sessionFactory;
	
	public boolean add(Vehicle vehicle) {
		sessionFactory.getCurrentSession().save(vehicle);
	    return true;
	}


}


