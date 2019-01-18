package edu.mondragon.springmvc.service;

import java.math.BigInteger;
import java.util.List;

import edu.mondragon.springmvc.entity.Line;
import edu.mondragon.springmvc.entity.Segment;
import edu.mondragon.springmvc.entity.Workstation;

public interface SegmentService {
	
	boolean addSegment(Segment segment);

	Line findCorrespondentLine(int id);
	
	Segment findSegment(int i);
	
	Workstation findWorkstation(int i);
	
	List<BigInteger> getWorkstationActivity();

}
