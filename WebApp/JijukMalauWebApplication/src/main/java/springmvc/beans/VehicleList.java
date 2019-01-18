package springmvc.beans;
import java.util.List;

import edu.mondragon.springmvc.entity.Vehicle;

public class VehicleList {
	List <Vehicle> vehicleList;
	
	public VehicleList(){}
	public List<Vehicle> getVehicleList() {
		return this.vehicleList;
	}
	public void setVehicleList(List <Vehicle> vehicleList){
		this.vehicleList = vehicleList;
	}
	public int getVehicleListSize() {
		return vehicleList.size();
	}
	public Vehicle get(int i) {
		return vehicleList.get(i);
	}
	
	
}
