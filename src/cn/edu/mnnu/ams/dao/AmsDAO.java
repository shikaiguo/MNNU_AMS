package cn.edu.mnnu.ams.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import cn.edu.mnnu.ams.entity.AlumniInfos;
import cn.edu.mnnu.ams.entity.Dept;
import cn.edu.mnnu.ams.entity.ExamineVerify;
import cn.edu.mnnu.ams.entity.Role;
import cn.edu.mnnu.ams.entity.User;

public class AmsDAO implements IAmsDAO {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public User getUser(String username) {
		String hql="FROM User WHERE username='"+username+"'";
		Session session=sessionFactory.getCurrentSession();
		List<User> ulist=session.createQuery(hql).list();
		if(ulist.size()>0)
			return ulist.get(0);
		return null;
	}

	@Override
	public List<AlumniInfos> getAlumniInfoList(String condition) {
		String hql="FROM AlumniInfos WHERE "+condition;
		if(condition.equals("all")) hql="From AlumniInfos";
		Session session=sessionFactory.getCurrentSession();
		List<AlumniInfos> list=session.createQuery(hql).list();
		return list;
	}

	@Override
	public AlumniInfos getAlumniInfo(int id) {
		Session session=sessionFactory.getCurrentSession();
		return (AlumniInfos) session.get(AlumniInfos.class, id);
	}

	@Override
	public String getAlumniInfoFiled(int bindid, String filed) {
		String hql="SELECT "+filed+" FROM AlumniInfos WHERE id="+bindid;
		Session session=sessionFactory.getCurrentSession();
		return (String)session.createQuery(hql).list().get(0);
	}

	@Override
	public void setAlumniInfoFiled(int id, String filed, String content) {
		String hql="UPDATE AlumniInfos SET "+filed+"='"+content+"' WHERE id='"+id+"'"; 
		Session session = sessionFactory.getCurrentSession();
		session.createQuery(hql).executeUpdate();
	}
	
	@Override
	public List<String> getProvinces() {
		String hql="SELECT provincename FROM Province ORDER BY provinceid ASC";
		Session session=sessionFactory.getCurrentSession();
		return session.createQuery(hql).list();
	}
	@Override
	public List<String> getCitys() {
		String hql="SELECT cityname FROM City ORDER BY cityid ASC";
		Session session=sessionFactory.getCurrentSession();
		return session.createQuery(hql).list();
	}

	@Override
	public int getProvinceCount(int provinceid, String table) {
		String hql="SELECT COUNT(*) FROM "+table+" WHERE provinceid='"+provinceid+"'";
		Session session = sessionFactory.getCurrentSession();
		Number num=(Number) session.createQuery(hql).list().get(0);
		return num.intValue();
	}
	
	@Override
	public int getCityCount(int cityid,String table) {
		String hql="SELECT COUNT(*) FROM "+table+" WHERE cityid='"+cityid+"'";
		Session session = sessionFactory.getCurrentSession();
		Number num=(Number) session.createQuery(hql).list().get(0);
		return num.intValue();
	}

	@Override
	public ExamineVerify getExamineVerify(int id) {
		Session session = sessionFactory.getCurrentSession();
		return (ExamineVerify) session.get(ExamineVerify.class, id);
	}

	@Override
	public List<ExamineVerify> getExamineVerifyList() {
		String hql = "From ExamineVerify";
		Session session = sessionFactory.getCurrentSession();
		List<ExamineVerify> list = session.createQuery(hql).list();
		return list;
	}
	
	@Override
	public void delExamineVerify(int id) {
		String hql="DELETE FROM ExamineVerify WHERE id="+id;
		Session session = sessionFactory.getCurrentSession();
		//session.delete((ExamineVerify) session.get(ExamineVerify.class, id));
		session.createQuery(hql).executeUpdate();
		
	}

	@Override
	public List<String> getDeptStringList() {
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
	public List<String> getProfessionStringList(String dept) {
		String hql="SELECT profession FROM AlumniInfos ";
		if(!dept.equals("所有"))
			hql+="WHERE dept='"+dept+"' ";
		hql+=" GROUP BY profession";
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery(hql).list();
	}

	@Override
	public int getProfessionCount(String profession, String dept) {
		String hql="SELECT COUNT(*) FROM AlumniInfos WHERE profession='"+profession+"'";
		if(!dept.equals("所有"))
			hql+=" AND dept='"+dept+"'";
		Session session = sessionFactory.getCurrentSession();
		Number num=(Number) session.createQuery(hql).list().get(0);
		return num.intValue();
	}

	@Override
	public List<Dept> getDeptList() {
		String hql="FROM Dept";
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery(hql).list();
	}

	@Override
	public void addDept(String id, String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void importAlumniInfos(List<AlumniInfos> list) {
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
	public void setPassword(String username, String password) {
		String hql="UPDATE User SET password='"+password+"' WHERE username='"+username+"'";
		Session session = sessionFactory.getCurrentSession();
		session.createQuery(hql).executeUpdate();
	}

	@Override
	public Role getRole(int role_id) {
		String hql="FROM Role WHERE roleid='"+role_id+"'";
		Session session=sessionFactory.getCurrentSession();
		return (Role) session.createQuery(hql).list().get(0);
	}

	@Override
	public void setLastLogintime(String username, long currentTimeMillis) {
		String hql="UPDATE User SET lastlogintime='"+currentTimeMillis+"' WHERE username='"+username+"'";
		Session session = sessionFactory.getCurrentSession();
		session.createQuery(hql).executeUpdate();
	}

	@Override
	public Role getRoleByRoletype(int type) {
		String hql="FROM Role WHERE roletype='"+type+"'";
		Session session=sessionFactory.getCurrentSession();
		return (Role) session.createQuery(hql).list().get(0);
	}
}
