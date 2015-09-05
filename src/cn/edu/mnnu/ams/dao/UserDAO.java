package cn.edu.mnnu.ams.dao;

import org.hibernate.SessionFactory;

public class UserDAO implements IUserDAO {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
