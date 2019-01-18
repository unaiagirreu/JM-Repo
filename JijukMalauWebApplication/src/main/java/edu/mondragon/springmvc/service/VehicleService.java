package edu.mondragon.springmvc.service;

import java.math.BigInteger;
import java.util.List;

import edu.mondragon.springmvc.entity.Vehicle;

public interface VehicleService {
	List<Vehicle> getAllVehicles();
	List<BigInteger> getVehicleActivity();
}
