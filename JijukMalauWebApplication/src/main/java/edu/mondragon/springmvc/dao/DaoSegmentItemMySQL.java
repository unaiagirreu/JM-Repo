package edu.mondragon.springmvc.dao;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.mondragon.springmvc.entity.Line;
import edu.mondragon.springmvc.entity.Segment;
import edu.mondragon.springmvc.entity.Workstation;



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

	@Override
	public Workstation findWorkstation(int id) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
	    TypedQuery<Workstation> query=sessionFactory.getCurrentSession().createQuery("from Segment where id='" + id+"'");
		return query.getSingleResult();
	}

	@Override
	public List<BigInteger> getWorkstationActivity() {
		TypedQuery<BigInteger> query= sessionFactory.getCurrentSession().createNativeQuery("select count(*) from product group by segmentOrigin_id;");
		return query.getResultList();
	}

	
}
