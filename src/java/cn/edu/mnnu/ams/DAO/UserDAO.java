package cn.edu.mnnu.ams.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import cn.edu.mnnu.ams.model.AlumniInfos;
import cn.edu.mnnu.ams.model.User;

public class UserDAO implements IUserDAO{
	SessionFactory sessionFactory;
	@SuppressWarnings("deprecation")
	public UserDAO(){
		sessionFactory=new Configuration().configure().buildSessionFactory();
		System.out.println("UserDAO init success.");
	}
	public String getPassword(String uid){
		String hql = "From User user where user.uid='" + uid + "'";
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<User> list = session.createQuery(hql).list();
		session.getTransaction().commit();
		return list.size()>0?list.get(0).getPassword():null;
	}
	public User getUserInfo(String uid){
		String hql = "From User user where user.uid='" + uid + "'";
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<User> list = session.createQuery(hql).list();
		session.getTransaction().commit();
		return list.size()>0?list.get(0):null;
	}
	public AlumniInfos getDetailById(String id){
		String hql="From AlumniInfos ai Where ai.id="+id;
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<AlumniInfos>list=session.createQuery(hql).list();
		session.getTransaction().commit();
		return list.size()>0?list.get(0):null;
	}
	public List<AlumniInfos> getAllInfos(String condition){
		String hql="From AlumniInfos Where "+condition;
		System.out.println(hql);
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<AlumniInfos>list=session.createQuery(hql).list();
		session.getTransaction().commit();
		return list;
	}
}
