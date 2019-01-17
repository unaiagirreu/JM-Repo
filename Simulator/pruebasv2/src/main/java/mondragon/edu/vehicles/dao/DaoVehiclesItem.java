package mondragon.edu.vehicles.dao;

import mondragon.edu.clases.Segment;
import mondragon.edu.clases.Vehicle;

/**
 * Interface for vehicle dao
 * 
 * @author unaiagirre
 *
 */
public interface DaoVehiclesItem {
	boolean add(Vehicle vehicle);

	boolean setStatus(Vehicle vehicle, String string);

	boolean setCurrentSegment(Vehicle vehicle, Segment currentSegment);

	boolean setDestinationSegment(Vehicle vehicle, Segment currentSegment);
}
