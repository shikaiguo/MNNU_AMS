package cn.edu.mnnu.ams.dao;

import java.util.List;

import cn.edu.mnnu.ams.model.AdminUser;
import cn.edu.mnnu.ams.model.AlumniInfos;
import cn.edu.mnnu.ams.model.CityStatistics;
import cn.edu.mnnu.ams.model.Dept;

public interface IAdminDAO {
	//从admin_users表 获取指定uid的管理员信息，存在返回AdminUser对象，不存在返回null
	public AdminUser getAllInfo(String uid) ;
	//从admin_users表 获取指定uid的管理员密码，存在返回String类型密码，不存在返回null
	public String getPassword(String uid);
	//向admin_users表 设置指定uid的管理员密码
	public void setPassword(String uid,String newPwd);
	//将校友信息列表List<AlumniInfos>存储到数据库alumni_infos表
	public void importAlumniInfos(List<AlumniInfos> list) ;
	//从alumni_infos表 按条件condition查询符合对象列表，返回List<AlumniInfos>对象(包含size=0)
	public List<AlumniInfos> queryAlumniInfos(String condition);
	//获取校友表中校友的各项统计
	public List<CityStatistics> getAreaStatisticsAll(String condition);
	public String getFromStatisticsCondition(String condition);
	//public String currentAllStatistics();
	//显示院系
	public List<Dept>queryDept();
	//增加院系
	public String addDept(String id,String name);
	//合并院系
	public String updateDept();
	//删除院系
	public String deleteDept();
}