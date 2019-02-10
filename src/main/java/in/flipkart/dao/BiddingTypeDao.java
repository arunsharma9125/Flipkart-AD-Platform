package in.flipkart.dao;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import in.flipkart.entity.BiddingType;

@Repository
public class BiddingTypeDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public void saveOrUpdate(BiddingType biddingType) {
		sessionFactory.getCurrentSession().saveOrUpdate(biddingType);

	}

	@Transactional
	public BiddingType getBiddingType() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(BiddingType.class);
		return (BiddingType) criteria.uniqueResult();
	}

}
