package cn.edu.mnnu.ams.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import cn.edu.mnnu.ams.func.Func;
import cn.edu.mnnu.ams.model.AdminUser;
import cn.edu.mnnu.ams.model.AlumniFrom;
import cn.edu.mnnu.ams.model.AlumniInfos;
@Repository 
public class AdminDAO implements IAdminDAO{

	SessionFactory sessionFactory;
	@SuppressWarnings("deprecation")
	public AdminDAO(){
		sessionFactory=new Configuration().configure().buildSessionFactory();
		System.out.println("AdminDAO init success.");
	}
	public AdminUser getAllInfo(String uid) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		AdminUser au=(AdminUser) session.get(AdminUser.class, uid);
		session.getTransaction().commit();
		return au;
	}
	public String getPassword(String uid) {
		Session session = sessionFactory.getCurrentSession();
	    session.beginTransaction();
	    AdminUser au=(AdminUser) session.get(AdminUser.class, uid);
		session.getTransaction().commit();
		return au==null?null:au.getPassword();
	}

	public void setPassword(String uid, String pwd) {
		String hql = "UPDATE AdminUser user SET user.password='" + pwd
				+ "' WHERE user.userid='" + uid + "'";
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.createQuery(hql).executeUpdate();
		session.getTransaction().commit();
	}

	public void importAlumniInfos(List<AlumniInfos> list) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		String hql = "";
		hql = "";
		for (int i = 0; i < list.size(); i++) {
			AlumniInfos ai = list.get(i);
			// 查重
			hql = "FROM AlumniInfos ai  WHERE ai.sno='" + ai.getSno()
					+ "' AND ai.sname='" + ai.getSname() + "' AND ai.dept='"
					+ ai.getDept() + "'";
			int ii = session.createQuery(hql).list().size();
			if (ii == 0) {
				AlumniFrom af=new AlumniFrom();
				session.save(ai);
				af.setId(ai.getId());
				af.setProvinceid(new Func().getProvinceID(ai.getProvincefrom()));
				af.setCityid(new Func().getCityID(ai.getCityfrom()));
				af.setDistrictid(new Func().getDistrictID(ai.getDistrictfrom()));
				session.saveOrUpdate(af);
			}
			if (i % 50 == 0) {
				session.flush();
				session.clear();
			}
		}
		session.getTransaction().commit();
	}

	public List<AlumniInfos> queryAlumniInfos(String condition/*AlumniInfos member='xxx' [AND ... ]*/) {
		String hql = "From AlumniInfos WHERE " + condition;
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<AlumniInfos> list = session.createQuery(hql).list();
		session.getTransaction().commit();
		return list;
	}
}
