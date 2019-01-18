package mondragon.edu.segment.dao;

import javax.persistence.TypedQuery;

import org.apache.commons.collections.bag.SynchronizedSortedBag;
import org.hibernate.IdentifierLoadAccess;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mondragon.edu.clases.Line;
import mondragon.edu.clases.Segment;

@Repository
public class DaoSegmentItemMySQL implements DaoSegmentItem{
	
	@Autowired
	private SessionFactory sessionFactory;

	public boolean add(Segment segment) {
		sessionFactory.getCurrentSession().save(segment);
	    return true;
	}
	
	@Override
	public Line findCorrespondentLine(int id) {
		//IdentifierLoadAccess<Line> query=sessionFactory.getCurrentSession().byId(Line.class);
		//return query.getReference(id);
		@SuppressWarnings("unchecked")
	    TypedQuery<Line> query=sessionFactory.getCurrentSession().createQuery("from Segment where id='" + id+"'");
		return query.getSingleResult();
	   }
	
	@Override
	public Segment findSegment(int id) {
		//IdentifierLoadAccess<Line> query=sessionFactory.getCurrentSession().byId(Line.class);
		//return query.getReference(id);
		@SuppressWarnings("unchecked")
	    TypedQuery<Segment> query=sessionFactory.getCurrentSession().createQuery("from Segment where id='" + id+"'");
		return query.getSingleResult();
	}

	
}
