package edu.mondragon.springmvc.dao;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import edu.mondragon.springmvc.entity.Vehicle;

@Repository
public class VehicleDaoImp implements VehicleDao{
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Vehicle> getAllVehicles(){
		@SuppressWarnings("unchecked")
	    TypedQuery<Vehicle> query= sessionFactory.getCurrentSession().createQuery("from Vehicle");
	    return query.getResultList();
	}

	@Override
	public List<BigInteger> getVehicleActivity() {
		TypedQuery<BigInteger> query= sessionFactory.getCurrentSession().createNativeQuery("select count(*) from product group by vehicle_id;");
		return query.getResultList();
	}

}	