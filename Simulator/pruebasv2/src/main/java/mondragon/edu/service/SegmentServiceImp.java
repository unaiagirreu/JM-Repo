package mondragon.edu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mondragon.edu.clases.Line;
import mondragon.edu.clases.Segment;
import mondragon.edu.segment.dao.DaoSegmentItem;

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

}
