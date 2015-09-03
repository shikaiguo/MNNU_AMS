package cn.edu.mnnu.ams.dao;

import org.hibernate.SessionFactory;

import cn.edu.mnnu.ams.entity.User;

public class UserDAO implements IUserDAO {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public User getUser(String uid) {
		User u = (User) sessionFactory.getCurrentSession().get(
				User.class, uid);
		return u;
	}
}
