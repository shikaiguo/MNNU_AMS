package cn.edu.mnnu.ams.dao;

import java.util.List;

import cn.edu.mnnu.ams.entity.Admin;
import cn.edu.mnnu.ams.entity.AlumniInfos;
import cn.edu.mnnu.ams.entity.Dept;
import cn.edu.mnnu.ams.entity.ExamineVerify;

public interface IAdminDAO {
	Admin getUser(String uid);
	List<AlumniInfos> getAlumniInfos(String condition);
	List<ExamineVerify> getExamineVerify();
	ExamineVerify getExamineVerifyItem(int id);
	void delExamineVerifyItem(int id);
	AlumniInfos getAlumniInfo(int id);
	String getFiledInfo(String infoid,String filed);
	void setFiledInfo(String infoid,String filed,String content);
	int getCityStatisticsCount(int cityid,String table);
	List<String> getProfessionArray(String dept);
	int getProfessionCount(String job,String dept);
	List<String> getDeptArray();
	int getDeptCount(String dept);
	List<Dept> getDept();
	void addDept(String id, String name);
	void addAlumniInfos(List<AlumniInfos> list);
}
