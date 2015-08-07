package cn.edu.mnnu.ams.DAO;

import java.util.List;

import cn.edu.mnnu.ams.model.AdminUser;
import cn.edu.mnnu.ams.model.AlumniInfos;

public interface IAdminDAO {
	//��admin_users�� ��ȡָ��uid�Ĺ���Ա��Ϣ�����ڷ���AdminUser���󣬲����ڷ���null
	public AdminUser getAllInfo(String uid) ;
	//��admin_users�� ��ȡָ��uid�Ĺ���Ա���룬���ڷ���String�������룬�����ڷ���null
	public String getPassword(String uid);
	//��admin_users�� ����ָ��uid�Ĺ���Ա����
	public void setPassword(String uid,String newPwd);
	//��У����Ϣ�б�List<AlumniInfos>�洢�����ݿ�alumni_infos��
	public void importAlumniInfos(List<AlumniInfos> list) ;
	//��alumni_infos�� ������condition��ѯ���϶����б�����List<AlumniInfos>����(����size=0)
	public List<AlumniInfos> queryAlumniInfos(String condition);
}
