package mondragon.edu.segment.dao;

import javax.persistence.TypedQuery;

import org.apache.commons.collections.bag.SynchronizedSortedBag;
import org.hibernate.IdentifierLoadAccess;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mondragon.edu.clases.Line;
import mondragon.edu.clases.Segment;

/**
 * Implements the segment dao
 * @author unaiagirre
 */
@Repository
public class DaoSegmentItemMySQL implements DaoSegmentItem{
	
	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * Adds a segment to the database
	 * @param segment the segment adding to the database
	 */
	public boolean add(Segment segment) {
		sessionFactory.getCurrentSession().save(segment);
	    return true;
	}
	
	/**
	 * Finds the correspondent line of a workstation or parking
	 * 
	 * @param id the id of the workstation or parking
	 * @return Line returns the correspondent line we where searching
	 */
	@Override
	public Line findCorrespondentLine(int id) {
		//IdentifierLoadAccess<Line> query=sessionFactory.getCurrentSession().byId(Line.class);
		//return query.getReference(id);
		@SuppressWarnings("unchecked")
	    TypedQuery<Line> query=sessionFactory.getCurrentSession().createQuery("from Segment where id='" + id+"'");
		return query.getSingleResult();
	   }

	/**
	 * Finds a segment by id
	 * 
	 * @param id the id of the segment we are searching
	 * @return Segment the segment we were finding
	 */
	@Override
	public Segment findSegment(int id) {
		//IdentifierLoadAccess<Line> query=sessionFactory.getCurrentSession().byId(Line.class);
		//return query.getReference(id);
		@SuppressWarnings("unchecked")
	    TypedQuery<Segment> query=sessionFactory.getCurrentSession().createQuery("from Segment where id='" + id+"'");
		return query.getSingleResult();
	}

	
}
