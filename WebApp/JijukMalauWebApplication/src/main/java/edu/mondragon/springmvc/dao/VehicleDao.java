package edu.mondragon.springmvc.dao;

import java.math.BigInteger;
import java.util.List;

import edu.mondragon.springmvc.entity.Vehicle;

public interface VehicleDao {
	List<Vehicle> getAllVehicles();

	List<BigInteger> getVehicleActivity();

}
