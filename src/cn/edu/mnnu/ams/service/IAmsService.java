package cn.edu.mnnu.ams.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import cn.edu.mnnu.ams.entity.AlumniInfos;
import cn.edu.mnnu.ams.entity.Dept;
import cn.edu.mnnu.ams.entity.User;
import cn.edu.mnnu.ams.model.EchartData;
import cn.edu.mnnu.ams.model.ExamineInfo;
import cn.edu.mnnu.ams.model.JqGridData;


public interface IAmsService {
	//登陆 返回值：0-错误 1-管理员 2-用户
	int Login(String uid,String pwd);
	//验证 返回值：0-失败 1-管理员 2-用户
	int VerifyUser(HttpSession session);
	User getUser(String username);
	JqGridData<AlumniInfos> queryAlumniInfos(String condition);
	AlumniInfos getAlumniInfoFromId(int id);
	List<ExamineInfo> getExamineInfos();
	
	List<EchartData> getAreaStatistics(String type);
	List<String> getProfessionType(String dept);
	List<EchartData> getProfessionStatistics(String dept);
	List<EchartData> getDeptStatistics();
	void passEV(String[] arr);
	void refuseEV(String[] arr);
	void flushEV(List<ExamineInfo> list);
	List<Dept> getDept();
	void addDept(String id, String name);
	List<AlumniInfos> readExcel(MultipartFile file) throws IOException;
	void importExcel(List<AlumniInfos> list);
	int updatePassword(String username, String opwd, String npwd);
}
