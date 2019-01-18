package edu.mondragon.springmvc.service;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.mondragon.springmvc.dao.DaoSegmentItem;
import edu.mondragon.springmvc.entity.Line;
import edu.mondragon.springmvc.entity.Segment;
import edu.mondragon.springmvc.entity.Workstation;



@Service
public class SegmentServiceImp implements SegmentService{
	
	@Autowired
	private DaoSegmentItem segmentDao;

	@Transactional
	public boolean addSegment(Segment segment) {
		return segmentDao.add(segment);
	}

	@Transactional
	@Override
	public Line findCorrespondentLine(int id) {
		// TODO Auto-generated method stub
		return segmentDao.findCorrespondentLine(id);
	}

	@Transactional
	@Override
	public Segment findSegment(int id) {
		// TODO Auto-generated method stub
		return segmentDao.findSegment(id);
	}

	@Transactional
	@Override
	public Workstation findWorkstation(int i) {
		// TODO Auto-generated method stub
		return segmentDao.findWorkstation(i);
	}

	@Transactional
	@Override
	public List<BigInteger> getWorkstationActivity() {
		return segmentDao.getWorkstationActivity();
	}

}
