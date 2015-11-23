package cn.edu.mnnu.ams.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import cn.edu.mnnu.ams.dao.IAmsDAO;
import cn.edu.mnnu.ams.entity.AlumniInfos;
import cn.edu.mnnu.ams.entity.Dept;
import cn.edu.mnnu.ams.entity.ExamineVerify;
import cn.edu.mnnu.ams.entity.Role;
import cn.edu.mnnu.ams.entity.User;
import cn.edu.mnnu.ams.func.Func;
import cn.edu.mnnu.ams.model.EchartData;
import cn.edu.mnnu.ams.model.ExamineInfo;
import cn.edu.mnnu.ams.model.JqGridData;

public class AmsService implements IAmsService {

	private IAmsDAO amsDao;
	private Func func = new Func();

	public void setAmsDao(IAmsDAO amsDao) {
		this.amsDao = amsDao;
	}

	@Override
	public User Login(String username, String password) {
		User u = amsDao.getUser(username);
		if (u != null) {
			try {
				if (func.md5(username+password).equals(u.getPassword())) {
					User user = amsDao.getUser(username);
					amsDao.setLastLogintime(username,System.currentTimeMillis());
					return user;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public int VerifyUser(HttpSession session) {
		if (session != null && session.getAttribute("role") != null) {
			Role role = (Role) session.getAttribute("role");
			if (role != null) { return role.getRoletype();}
		}
		return 0;
	}

	@Override
	public User getUser(String username) {
		if (username != null) {
			User user = amsDao.getUser(username);
			return user;
		}
		return null;
	}

	@Override
	public JqGridData<AlumniInfos> queryAlumniInfos(String condition) {
		JqGridData<AlumniInfos> ulist = new JqGridData<AlumniInfos>();
		List<AlumniInfos> alumniList = new ArrayList<AlumniInfos>();
		// int rowsInt = Integer.parseInt(rows);
		// int pageInt = Integer.parseInt(page);
		// ulist.setPage(pageInt);
		// ulist.setRows(rowsInt);
		if (condition == null) return ulist;
		condition = condition.replace("&", " AND ");
		alumniList = amsDao.getAlumniInfoList(condition);
		// int totalRecord = alumniList.size();
		// ulist.setRecords(totalRecord);
		// int totalPage = totalRecord % rowsInt == 0 ? totalRecord / rowsInt
		// : totalRecord / rowsInt + 1;
		// ulist.setTotal(totalPage);
		ulist.setGridModel(alumniList);
		return ulist;
		// return null;
	}

	@Override
	public AlumniInfos getAlumniInfoFromId(int id) {
		return amsDao.getAlumniInfo(id);
	}

	@Override
	public List<ExamineInfo> getExamineInfos() {
		List<ExamineVerify> list_ev = amsDao.getExamineVerifyList();
		List<ExamineInfo> list_ei = new ArrayList<ExamineInfo>();
		String[] fileds = { "sno", "dept", "major", "class", "sname", "sex",
				"province_from", "city_from", "district_from", "degree",
				"gra_time", "province_work", "city_work", "district_work",
				"work_unit", "duty", "job", "profession", "phone1", "phone2",
				"mail", "qq", "address", "zip", "note", "assoc1", "assoc1_job",
				"assoc2", "assoc2_job" };
		String[] filedNames = { "学号", "学院", "专业", "班级", "姓名", "性别", "生源地省",
				"生源地市", "生源地县", "学历", "毕业时间", "工作省份", "工作市", "工作地区", "工作单位",
				"职务", "职称", "行业", "联系电话", "固定电话", "邮箱", "QQ", "通讯地址", "邮编",
				"备注", "所属校友总会", "校友总会职务", "所属校友分会", "校友分会职务" };

		for (int i = 0; i < list_ev.size(); i++) {
			ExamineInfo ei = new ExamineInfo(list_ev.get(i));
			int bindid = amsDao.getUser(ei.getUserid()).getBindid();
			ei.setOldcontent( amsDao.getAlumniInfoFiled(bindid, ei.getFiled()));
			for (int j = 0; j < fileds.length; j++) {
				if (ei.getFiled().equals(fileds[j])) {
					ei.setFiled(filedNames[j]);
					break;
				}
			}
			list_ei.add(ei);
		}
		return list_ei;
	}

	@Override
	public List<EchartData> getAreaStatistics(String table) {
		List<String>provinceList=amsDao.getProvinces();
		List<String>cityList=amsDao.getCitys();
		provinceList.add(0,"未填写");cityList.add(0,"未填写");
		List<EchartData> list = new ArrayList<EchartData>();
		for (int i = 0; i < provinceList.size(); i++) {
			list.add(new EchartData(provinceList.get(i), amsDao.getProvinceCount(
					i, table)));
		}
		for (int i = 0; i < cityList.size(); i++) {
			list.add(new EchartData(cityList.get(i), amsDao.getCityCount(
					i, table)));
		}
		return list;
	}

	@Override
	public void passEV(String[] arr) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i].equals("")) continue;
			ExamineVerify ev = amsDao.getExamineVerify(Integer
					.parseInt(arr[i]));
			
			 User u = amsDao.getUser(ev.getUserid());
			 amsDao.setAlumniInfoFiled(u.getBindid(), ev.getFiled(),
			 ev.getContent());
			 amsDao.delExamineVerify(Integer.parseInt(arr[i]));
		}
	}

	@Override
	public void refuseEV(String[] arr) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i].equals("")) continue;
			amsDao.delExamineVerify(Integer.parseInt(arr[i]));
		}
	}

	@Override
	public void flushEV(List<ExamineInfo> list) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getOldcontent().equals(list.get(i).getContent())) {
				amsDao.delExamineVerify(list.get(i).getId());
			}
		}
	}

	@Override
	public List<EchartData> getDeptStatistics() {
		List<String> dept_arr = amsDao.getDeptStringList();
		List<EchartData> list_e = new ArrayList<EchartData>();
		for (int i = 0; i < dept_arr.size(); i++) {
			list_e.add(new EchartData(dept_arr.get(i), amsDao
					.getDeptCount(dept_arr.get(i))));
		}
		return list_e;
	}

	@Override
	public List<String> getProfessionType(String dept) {
		List<String> list=amsDao.getProfessionStringList(dept);
		for(int i=0;i<list.size();i++){
			if(list.get(i).equals("")){
				list.set(i,	"未填写");
				break;
			}
		}
		return list;
	}

	@Override
	public List<EchartData> getProfessionStatistics(String dept) {
		List<String> duty_arr = amsDao.getProfessionStringList(dept);
		List<EchartData> list_e = new ArrayList<EchartData>();
		for (int i = 0; i < duty_arr.size(); i++) {
			list_e.add(new EchartData(duty_arr.get(i).equals("")?"未填写":duty_arr.get(i), amsDao
					.getProfessionCount(duty_arr.get(i), dept)));
		}
		return list_e;
	}

	@Override
	public List<Dept> getDept() {
		return amsDao.getDeptList();
	}

	@Override
	public void addDept(String id, String name) {
		amsDao.addDept(id, name);
	}

	@Override
	public List<AlumniInfos> readExcel(MultipartFile file) throws IOException {
		InputStream stream = file.getInputStream();
		Workbook wb = null;
		if (file.getContentType().equals("application/vnd.ms-excel")) {
			wb = new HSSFWorkbook(stream);
		} else {
			wb = new XSSFWorkbook(stream);
		}
		stream.close();
		Sheet s1 = wb.getSheetAt(0);
		String value = "";
		List<AlumniInfos> list = new ArrayList<AlumniInfos>();
		// 处理表头
		Row row = s1.getRow(0);
		int[] arr = new int[29];
		if (row == null) {
			wb.close();
			return null;// "表格内容出错，请先检查";
		}
		for (int celli = 0; celli < row.getLastCellNum(); celli++) {
			Cell cell = row.getCell(celli);
			if (cell == null) continue;
			String headString = cell.getStringCellValue();
			if (headString.equals("系别")) arr[celli] = 0;
			else if (headString.equals("专业")) arr[celli] = 1;
			else if (headString.equals("班级")) arr[celli] = 2;
			else if (headString.equals("学号")) arr[celli] = 3;
			else if (headString.equals("姓名")) arr[celli] = 4;
			else if (headString.equals("性别")) arr[celli] = 5;
			else if (headString.equals("生源地省")) arr[celli] = 6;
			else if (headString.equals("生源地市")) arr[celli] = 7;
			else if (headString.equals("生源地区")) arr[celli] = 8;
			else if (headString.equals("学历")) arr[celli] = 9;
			else if (headString.equals("毕业时间")) arr[celli] = 10;
			else if (headString.equals("工作地省")) arr[celli] = 11;
			else if (headString.equals("工作地市")) arr[celli] = 12;
			else if (headString.equals("工作地区")) arr[celli] = 13;
			else if (headString.equals("工作单位")) arr[celli] = 14;
			else if (headString.equals("职务")) arr[celli] = 15;
			else if (headString.equals("职称")) arr[celli] = 16;
			else if (headString.equals("行业")) arr[celli] = 17;
			else if (headString.equals("联系电话")) arr[celli] = 18;
			else if (headString.equals("固定电话")) arr[celli] = 19;
			else if (headString.equals("邮箱")) arr[celli] = 20;
			else if (headString.equals("QQ号")) arr[celli] = 21;
			else if (headString.equals("联系地址")) arr[celli] = 22;
			else if (headString.equals("邮编")) arr[celli] = 23;
			else if (headString.equals("备注")) arr[celli] = 24;
			else if (headString.equals("所属校友会")) arr[celli] = 25;
			else if (headString.equals("所属校友会职务")) arr[celli] = 26;
			else if (headString.equals("所属校友分会")) arr[celli] = 27;
			else if (headString.equals("所属校友分会职务")) arr[celli] = 28;
		}
		// 处理表格
		for (int rowi = 1; rowi <= s1.getLastRowNum(); rowi++) {
			row = s1.getRow(rowi);
			if (row == null) continue;
			AlumniInfos ai = new AlumniInfos();
			for (int celli = 0; celli < row.getLastCellNum(); celli++) {
				Cell cell = row.getCell(celli);
				if (cell == null) continue;
				switch (cell.getCellType()) {
					case HSSFCell.CELL_TYPE_NUMERIC:
						// 对数字进行格式化，使其不自动转化为科学计数法。
						java.text.NumberFormat nf = java.text.NumberFormat
								.getInstance();
						nf.setGroupingUsed(false);
						value = nf
								.format(new Double(cell.getNumericCellValue()));
						break;
					case HSSFCell.CELL_TYPE_STRING:
						value = cell.getStringCellValue();
						break;
					default:
						value = "";
				}
				// 在ai.setXXX()前编写代码对该属性进行处理
				switch (arr[celli]) {
					case 0:
						ai.setDept(value);
						break;
					case 1:
						ai.setMajor(value);
						break;
					case 2:
						ai.setCls(value);
						break;
					case 3:
						ai.setSno(value);
						break;
					case 4:
						ai.setSname(value);
						break;
					case 5:
						ai.setSex(value);
						break;
					case 6:
						ai.setProvincefrom(value);
						break;
					case 7:
						ai.setCityfrom(value);
						break;
					case 8:
						ai.setDistrictfrom(value);
						break;
					case 9:
						ai.setDegree(value);
						break;
					case 10:
						ai.setGratime(value);
						break;
					case 11:
						ai.setProvincework(value);
						break;
					case 12:
						ai.setCitywork(value);
						break;
					case 13:
						ai.setDistrictwork(value);
						break;
					case 14:
						ai.setWorkunit(value);
						break;
					case 15:
						ai.setDuty(value);
						break;
					case 16:
						ai.setJob(value);
						break;
					case 17:
						ai.setProfession(value);
						break;
					case 18:
						ai.setPhone1(value);
						break;
					case 19:
						ai.setPhone2(value);
						break;
					case 20:
						ai.setMail(value);
						break;
					case 21:
						ai.setQq(value);
						break;
					case 22:
						ai.setAddress(value);
						break;
					case 23:
						ai.setZip(value);
						break;
					case 24:
						ai.setNote(value);
						break;
					case 25:
						ai.setAssoc1(value);
						break;
					case 26:
						ai.setAssoc1job(value);
						break;
					case 27:
						ai.setAssoc2(value);
						break;
					case 28:
						ai.setAssoc2job(value);
						break;
				}
			}
			list.add(ai);
		}
		return list;
	}

	@Override
	public void importExcel(List<AlumniInfos> list) {
		amsDao.importAlumniInfos(list);
	}

	@Override
	public int updatePassword(String username, String opwd, String npwd) {
		if(npwd.length()<6||npwd.length()>16)return 0;
		User user=amsDao.getUser(username);
		if(user.getPassword().equals(func.md5(username+opwd))){
			amsDao.setPassword(username,func.md5(username+npwd));
			return 1;
		}
		return 0;
	}

	@Override
	public int getRoleType(int role_id) {
		Role role=amsDao.getRole(role_id);
		if(role != null){
			return role.getRoletype();
		}
		return 0;
	}

	@Override
	public Role getRole(int role_id) {
		return amsDao.getRole(role_id);
	}

}
