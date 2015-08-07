package cn.edu.mnnu.ams.DAO;

import java.util.List;

import cn.edu.mnnu.ams.model.AdminUser;
import cn.edu.mnnu.ams.model.AlumniInfos;

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
}
