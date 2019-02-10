package in.flipkart.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import in.flipkart.entity.User;

@Repository
public class UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public void saveOrUpdate(User user) {
		sessionFactory.getCurrentSession().saveOrUpdate(user);
	}

	/*
	@Transactional(readOnly = true)
	public User getByEmail(String email) {
		CriteriaBuilder builder = sessionFactory.getCriteriaBuilder();
		CriteriaQuery<User> query = builder.createQuery(User.class);
		Root<User> root = query.from(User.class);
		query.select(root).where(builder.equal(root.get("email"), email));
		Query<User> q = sessionFactory.getCurrentSession().createQuery(query);
		User user = null;
		try{
			user = q.getSingleResult();
		}catch (NoResultException nre){}
		return user;
	}
	*/
	
	@Transactional(readOnly = true)
	public User getByEmail(String email) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("email", email));
		return (User) criteria.uniqueResult();
	}
	
	@Transactional
	public List<User> listUser(){
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
		return criteria.list();
	}
}
