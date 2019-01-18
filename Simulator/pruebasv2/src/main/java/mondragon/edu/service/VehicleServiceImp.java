package mondragon.edu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mondragon.edu.clases.Vehicle;
import mondragon.edu.vehicles.dao.DaoVehiclesItem;

@Service
public class VehicleServiceImp implements VehicleService{
	
	@Autowired
	private DaoVehiclesItem vehiclesDao;

	@Transactional
	public boolean add(Vehicle vehicle) {
		return vehiclesDao.add(vehicle);
	}

}
