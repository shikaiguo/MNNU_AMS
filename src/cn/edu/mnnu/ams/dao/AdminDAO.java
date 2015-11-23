package cn.edu.mnnu.ams.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import cn.edu.mnnu.ams.entity.AlumniInfos;
import cn.edu.mnnu.ams.entity.Dept;
import cn.edu.mnnu.ams.entity.ExamineVerify;
import cn.edu.mnnu.ams.entity.Role;
import cn.edu.mnnu.ams.entity.User;

public class AdminDAO implements IAdminDAO {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public User getUser(String uid) {
		User au = (User) sessionFactory.getCurrentSession().get(
				User.class, uid);
		return au;
	}
	@Override
	public List<AlumniInfos> getAlumniInfos(String condition) {
		String hql = "From AlumniInfos WHERE " + condition;
		Session session = sessionFactory.getCurrentSession();
		List<AlumniInfos> list = session.createQuery(hql).list();
		return list;
	}

	@Override
	public List<ExamineVerify> getExamineVerify() {
		String hql = "From ExamineVerify";
		Session session = sessionFactory.getCurrentSession();
		List<ExamineVerify> list = session.createQuery(hql).list();
		return list;
	}

	@Override
	public AlumniInfos getAlumniInfo(int id) {
		Session session = sessionFactory.getCurrentSession();
		return (AlumniInfos) session.get(AlumniInfos.class, id);
	}

	@Override
	public String getFiledInfo(String infoid, String filed) {
		String hql="SELECT "+filed+" FROM AlumniInfos WHERE id='"+infoid+"'"; 
		Session session = sessionFactory.getCurrentSession();
		String filedContent= (String) session.createQuery(hql).list().get(0);
		return filedContent;
	}

	@Override
	public void setFiledInfo(String infoid, String filed,String content) {
		String hql="UPDATE AlumniInfos SET "+filed+"='"+content+"' WHERE id='"+infoid+"'"; 
		Session session = sessionFactory.getCurrentSession();
		session.createQuery(hql).executeUpdate();
	}

	@Override
	public int getCityStatisticsCount(int cityid,String table) {
		String hql="SELECT COUNT(*) FROM "+table+" WHERE cityid='"+cityid+"'";
		Session session = sessionFactory.getCurrentSession();
		Number num=(Number) session.createQuery(hql).list().get(0);
		return num.intValue();
	}

	@Override
	public ExamineVerify getExamineVerifyItem(int id) {
		Session session = sessionFactory.getCurrentSession();
		return (ExamineVerify) session.get(ExamineVerify.class, id);
	}

	@Override
	public void delExamineVerifyItem(int id) {
		String hql="DELETE FROM ExamineVerify WHERE id="+id;
		Session session = sessionFactory.getCurrentSession();
		//session.delete((ExamineVerify) session.get(ExamineVerify.class, id));
		session.createQuery(hql).executeUpdate();
	}

	@Override
	public List<String> getProfessionArray(String dept) {
		String hql="SELECT profession FROM AlumniInfos ";
		if(!dept.equals("所有"))
			hql+="WHERE dept='"+dept+"' ";
		hql+=" GROUP BY profession";
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery(hql).list();
	}
	@Override
	public int getProfessionCount(String job,String dept) {
		String hql="SELECT COUNT(*) FROM AlumniInfos WHERE profession='"+job+"'";
		if(!dept.equals("所有"))
			hql+=" AND dept='"+dept+"'";
		Session session = sessionFactory.getCurrentSession();
		Number num=(Number) session.createQuery(hql).list().get(0);
		return num.intValue();
	}
	@Override
	public List<String> getDeptArray() {
		String hql="SELECT dept FROM AlumniInfos GROUP BY dept";
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery(hql).list();
	}

	@Override
	public int getDeptCount(String dept) {
		String hql="SELECT COUNT(*) FROM AlumniInfos WHERE dept='"+dept+"'";
		Session session = sessionFactory.getCurrentSession();
		Number num=(Number) session.createQuery(hql).list().get(0);
		return num.intValue();
	}
	@Override
	public List<Dept> getDept() {
		String hql="FROM Dept";
		Session session = sessionFactory.getCurrentSession();
		return  session.createQuery(hql).list();
	}

	@Override
	public void addDept(String id, String name) {
		Dept d=new Dept();
		d.setId(id);
		d.setName(name);
		Session session=sessionFactory.getCurrentSession();
		session.save(d);
	}

	@Override
	public void addAlumniInfos(List<AlumniInfos> list) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "";
		for (int i = 0; i < list.size(); i++) {
			AlumniInfos ai = list.get(i);
			// 查重
			hql = "FROM AlumniInfos ai  WHERE ai.sno='" + ai.getSno()
					+ "' AND ai.sname='" + ai.getSname() + "' AND ai.dept='"
					+ ai.getDept() + "'";
			int ii = session.createQuery(hql).list().size();
			if (ii == 0) {
				session.save(ai);
				/*
				AlumniFrom af=new AlumniFrom();
				AlumniWork aw=new AlumniWork();
				af.setId(ai.getId());
				af.setProvinceid(new Func().getProvinceID(ai.getProvincefrom()));
				af.setCityid(new Func().getCityID(ai.getCityfrom()));
				af.setDistrictid(new Func().getDistrictID(ai.getDistrictfrom()));
				
				aw.setId(ai.getId());
				aw.setProvinceid(new Func().getProvinceID(ai.getProvincework()));
				aw.setCityid(new Func().getCityID(ai.getCitywork()));
				aw.setDistrictid(new Func().getDistrictID(ai.getDistrictwork()));
				session.saveOrUpdate(af);
				session.saveOrUpdate(aw);*/
			}
			if (i % 50 == 0) {
				session.flush();
				session.clear();
			}
		}
	}

	@Override
	public List<User> getUserListByRoleid(int roleid) {
		String hql="FROM User WHERE roleid='"+roleid+"'";
		Session session=sessionFactory.getCurrentSession();
		return session.createQuery(hql).list();
	}
}
