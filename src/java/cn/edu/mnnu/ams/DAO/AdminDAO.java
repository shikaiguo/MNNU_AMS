package cn.edu.mnnu.ams.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import cn.edu.mnnu.ams.model.AdminUser;
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
		String hql = "From AdminUser user where user.userid='" + uid + "'";
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<AdminUser> list = session.createQuery(hql).list();
		session.getTransaction().commit();
		return list.size()>0?list.get(0):null;
	}
	public String getPassword(String uid) {
		String hql = "From AdminUser user where user.userid='" + uid + "'";
		Session session = sessionFactory.getCurrentSession();
	    session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<AdminUser> list = session.createQuery(hql).list();
		session.getTransaction().commit();
		return list.size()>0? list.get(0).getPassword():null;
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
			// ²éÖØ
			hql = "FROM AlumniInfos ai  WHERE ai.sno='" + ai.getSno()
					+ "' AND ai.sname='" + ai.getSname() + "' AND ai.dept='"
					+ ai.getDept() + "'";
			int ii = session.createQuery(hql).list().size();
			if (ii == 0) {
				session.save(ai);
				//AlumniFrom af = new AlumniFrom();
				/*
				af.setId(ai.getId());
				// Ê¡id
				int provinceid = new Func().getProvinceID(ai.getProvincefrom());
				af.setProvinceid(provinceid);
				int cityid = 0;
				String cname = ai.getSfrom();
				switch (provinceid) {
					case 0:
						break;
					case 5:
						;
					case 8:
						if (cname.length() >= 1) cname = cname.substring(1);
					default:
						if (cname.length() >= 2) cname = cname.substring(2);
				}
				String district = new Func().getCityID(cname);
				if (district != null) {
					cityid = Integer.parseInt(district.split(":")[1]);
					af.setCityid(cityid);
					district = district.split(":")[0];
					if (!district.equals("")) {
						af.setDistrictid(new Func().getDistrictID(district));
					}
				}
				if (provinceid == 0 && cityid != 0) {
					hql = "From City c WHERE c.cityid='" + cityid + "'";
					af.setProvinceid(((City) session.createQuery(hql).list()
							.get(0)).getProvinceid());
				}
				session.save(af);
				*/
			}
			if (i % 50 == 0) {
				session.flush();
				session.clear();
			}
		}
		session.getTransaction().commit();
		session.close();
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
