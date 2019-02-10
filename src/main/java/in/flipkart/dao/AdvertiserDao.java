package in.flipkart.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import in.flipkart.entity.Advertiser;

@Repository
public class AdvertiserDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public void saveOrUpdate(Advertiser advertiser) {
		sessionFactory.getCurrentSession().saveOrUpdate(advertiser);		
	}
	
	@Transactional
	public List<Advertiser> listAdvertiser(){
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Advertiser.class);
		return criteria.list();
	}
	
	@Transactional
	public Advertiser findById(String id){
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Advertiser.class);
		criteria.add(Restrictions.eq("id", id));
		return (Advertiser) criteria.uniqueResult();
	}

}
