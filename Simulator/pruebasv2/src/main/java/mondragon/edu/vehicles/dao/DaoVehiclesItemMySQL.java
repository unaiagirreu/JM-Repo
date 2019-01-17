package mondragon.edu.vehicles.dao;


import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mondragon.edu.clases.Order;
import mondragon.edu.clases.Segment;
import mondragon.edu.clases.Vehicle;

/**
 * Implements vehicles dao
 * 
 * @author unaiagirre
 *
 */
@Repository
public class DaoVehiclesItemMySQL implements DaoVehiclesItem{

	@Autowired
	private SessionFactory sessionFactory;
	
	public boolean add(Vehicle vehicle) {
		sessionFactory.getCurrentSession().save(vehicle);
	    return true;
	}

	@Transactional
	@Override
	public boolean setStatus(Vehicle vehicle, String string) {
		@SuppressWarnings("unchecked")
		TypedQuery<Vehicle> query=sessionFactory.getCurrentSession().createQuery("update Vehicle set status = '"+string + "' where id='"+(vehicle.getId()+1)+"'");
		query.executeUpdate();
		return true;
	}

	@Transactional
	@Override
	public boolean setCurrentSegment(Vehicle vehicle, Segment currentSegment) {
		@SuppressWarnings("unchecked")
		TypedQuery<Vehicle> query=sessionFactory.getCurrentSession().createQuery("update Vehicle set currentSegment = '"+currentSegment.getId() + "' where id='"+(vehicle.getId()+1)+"'");
		query.executeUpdate();
		return true;
	}

	@Transactional
	@Override
	public boolean setDestinationSegment(Vehicle vehicle, Segment currentSegment) {
		@SuppressWarnings("unchecked")
		TypedQuery<Vehicle> query=sessionFactory.getCurrentSession().createQuery("update Vehicle set destinationSegment = '"+currentSegment.getId() + "' where id='"+(vehicle.getId()+1)+"'");
		query.executeUpdate();
		return true;
	}

}


