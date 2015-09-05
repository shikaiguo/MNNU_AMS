package cn.edu.mnnu.ams.dao;

import java.util.List;

import cn.edu.mnnu.ams.entity.AlumniInfos;
import cn.edu.mnnu.ams.entity.Dept;
import cn.edu.mnnu.ams.entity.ExamineVerify;
import cn.edu.mnnu.ams.entity.User;

public interface IAmsDAO {
	User getUser(String username);
	List<AlumniInfos> getAlumniInfoList(String condition);
	AlumniInfos getAlumniInfo(int id);
	String getAlumniInfoFiled(int bindid, String filed);
	void setAlumniInfoFiled(int bindid, String filed, String content);
	List<String> getProvinces();
	List<String> getCitys();
	int getProvinceCount(int provinceid,String table);
	int getCityCount(int cityid, String table);
	ExamineVerify getExamineVerify(int id);
	List<ExamineVerify> getExamineVerifyList();
	void delExamineVerify(int id);
	List<String> getDeptStringList();
	int getDeptCount(String dept);
	List<String> getProfessionStringList(String dept);
	int getProfessionCount(String string, String dept);
	List<Dept> getDeptList();
	void addDept(String id, String name);
	void importAlumniInfos(List<AlumniInfos> list);
	void setPassword(String username, String password);
}
