package cn.edu.mnnu.ams.DAO;

import java.util.List;

import cn.edu.mnnu.ams.model.AlumniInfos;
import cn.edu.mnnu.ams.model.User;

public interface IUserDAO {
	//��users�� ��ȡָ��uid���û����� ���ڷ���String�����룬���򷵻�null
	public String getPassword(String uid);
	//��users�� ��ȡָ��uid���û���Ϣ ���ڷ���User���󣬷��򷵻�null
	public User getUserInfo(String uid);
	//��alumni_infos���ȡָ��id��У����Ϣ�����ڷ���AlumniInfos���󣬷��򷵻�null
	public AlumniInfos getDetailById(String id);
	//��alumni_infos�� ��ָ������condition��ѯ���϶����б�����List<AlumniInfos>����(����size=0)
	public List<AlumniInfos> getAllInfos(String condition);
}
