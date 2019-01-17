package mondragon.edu.segment.dao;

import mondragon.edu.clases.Line;
import mondragon.edu.clases.Segment;

/**
 * Interface of the segment dao
 * 
 * @author unaiagirre
 *
 */
public interface DaoSegmentItem {
	
	boolean add(Segment segment);

	Line findCorrespondentLine(int id);
	
	Segment findSegment(int id);

}
