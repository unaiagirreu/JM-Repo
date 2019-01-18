package edu.mondragon.springmvc.dao;

import java.math.BigInteger;
import java.util.List;

import edu.mondragon.springmvc.entity.Line;
import edu.mondragon.springmvc.entity.Segment;
import edu.mondragon.springmvc.entity.Workstation;

public interface DaoSegmentItem {
	
	boolean add(Segment segment);

	Line findCorrespondentLine(int id);
	
	Segment findSegment(int id);
	
	Workstation findWorkstation(int id);

	List<BigInteger> getWorkstationActivity();

}
