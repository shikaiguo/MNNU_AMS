package cn.edu.mnnu.ams.DAO;

import java.util.List;

import cn.edu.mnnu.ams.model.AlumniInfos;
import cn.edu.mnnu.ams.model.User;

public interface IUserDAO {
	//从users表 获取指定uid的用户密码 存在返回String型密码，否则返回null
	public String getPassword(String uid);
	//从users表 获取指定uid的用户信息 存在返回User对象，否则返回null
	public User getUserInfo(String uid);
	//从alumni_infos表获取指定id的校友信息，存在返回AlumniInfos对象，否则返回null
	public AlumniInfos getDetailById(String id);
	//从alumni_infos表 按指定条件condition查询符合对象列表，返回List<AlumniInfos>对象(包含size=0)
	public List<AlumniInfos> getAllInfos(String condition);
}
