package in.flipkart.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import in.flipkart.entity.Advertisement;
import in.flipkart.entity.User.Gender;

@Repository
public class AdvertisementDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public void saveOrUpdate(Advertisement advertisement) {
		sessionFactory.getCurrentSession().saveOrUpdate(advertisement);
	}
	
	@Transactional
	public List<Advertisement> listAdvertisement(){
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Advertisement.class);
		return criteria.list();
	}
	
	@Transactional
	public List<Advertisement> listAdvertisement(Gender gender, Integer age){
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Advertisement.class);
		criteria.add(Restrictions.eq("gender", gender));
		criteria.add(Restrictions.le("startAge", age));
		criteria.add(Restrictions.ge("endAge", age));
		return criteria.list();
	}
}
