package cn.edu.mnnu.ams.service;

import java.util.List;

import cn.edu.mnnu.ams.entity.Admin;
import cn.edu.mnnu.ams.entity.AlumniInfos;
import cn.edu.mnnu.ams.entity.Dept;
import cn.edu.mnnu.ams.entity.User;
import cn.edu.mnnu.ams.model.EchartData;
import cn.edu.mnnu.ams.model.ExamineInfo;


public interface IAmsService {
	//登陆 返回值：0-信息错误  1-管理员 2-用户
	int Login(String uid,String pwd);
	Admin getAdminUser(String uid);
	User getUser(String uid);
	List<AlumniInfos> getAlumniInfo(String condition);
	AlumniInfos getAlumniInfoItem(String id);
	List<ExamineInfo> getExamineInfo();
	List<EchartData> getAreaStatistics(String type);
	List<String> getJobType(String dept);
	List<EchartData> getJobStatistics(String dept);
	List<EchartData> getDeptStatistics();
	void passEV(String[] arr);
	void refuseEV(String[] arr);
	void flushEV(List<ExamineInfo> list);
	List<Dept> getDept();
	void addDept(String id, String name);
}
