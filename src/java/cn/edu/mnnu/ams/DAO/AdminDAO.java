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
	@Override
	public AdminUser getAllInfo(String uid) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		AdminUser au=(AdminUser) session.get(AdminUser.class, uid);
		session.getTransaction().commit();
		return au;
	}
	@Override
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

	@Override
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

	@Override
	public List<AlumniInfos> queryAlumniInfos(String condition/*AlumniInfos member='xxx' [AND ... ]*/) {
		String hql = "From AlumniInfos WHERE " + condition;
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<AlumniInfos> list = session.createQuery(hql).list();
		session.getTransaction().commit();
		return list;
	}
	@Override
	public String getFromStatisticsAll() {
		Session session=sessionFactory.getCurrentSession();
		session.beginTransaction();
		List<AlumniFrom> list=session.createQuery("FROM AlumniFrom").list();
		session.getTransaction().commit();
		String res="{";
		String []province={"其他","北京市","天津市","河北省","山西省","内蒙古自治区","辽宁省","吉林省","黑龙江省","上海市","江苏省","浙江省","安徽省","福建省","江西省","山东省","河南省","湖北省","湖南省","广东省","广西壮族自治区","海南省","重庆市","四川省","贵州省","云南省","西藏自治区","陕西省","甘肃省","青海省","宁夏回族自治区","新疆维吾尔自治区","香港特别行政区","澳门特别行政区","台湾省"};
		int []count=new int[35];
		for(int i=0;i<35;i++)
			count[i]=0;
		for(int i=0;i<list.size();i++){
			count[list.get(i).getProvinceid()]++;
		}
		for(int i=0;i<35;i++){
			if(i!=0)res+=",";
			res+="\""+province[i]+"\":"+count[i];
		}
		return res+"}";
	}
	@Override
	public String getFromStatisticsCondition(String condition) {
		// TODO Auto-generated method stub
		return null;
	}
}
