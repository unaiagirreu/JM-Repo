package edu.mondragon.springmvc.service;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.mondragon.springmvc.dao.VehicleDao;
import edu.mondragon.springmvc.entity.Vehicle;
@Service
public class VehicleServiceImp implements VehicleService{
	@Autowired
	private VehicleDao vehicleDao;

	@Transactional
	public List<Vehicle> getAllVehicles() {
		return vehicleDao.getAllVehicles();
	}

	@Transactional
	@Override
	public List<BigInteger> getVehicleActivity() {
		return vehicleDao.getVehicleActivity();
	}
}
