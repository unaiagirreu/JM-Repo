package mondragon.edu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mondragon.edu.clases.Segment;
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

	@Override
	public boolean setStatus(Vehicle vehicle, String string) {
		// TODO Auto-generated method stub
		return vehiclesDao.setStatus(vehicle,string);
	}

	@Override
	public boolean setCurrentSegment(Vehicle vehicle, Segment currentSegment) {
		// TODO Auto-generated method stub
		return vehiclesDao.setCurrentSegment(vehicle,currentSegment);
	}

	@Override
	public boolean setDestinationSegment(Vehicle vehicle, Segment currentSegment) {
		// TODO Auto-generated method stub
		return vehiclesDao.setDestinationSegment(vehicle, currentSegment);
	}

}
