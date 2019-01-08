package mondragon.edu.service;

import mondragon.edu.clases.Line;
import mondragon.edu.clases.Segment;

public interface SegmentService {
	
	boolean addSegment(Segment segment);

	Line findCorrespondentLine(int id);
	
	Segment findSegment(int i);

}
