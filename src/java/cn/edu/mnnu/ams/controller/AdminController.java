package cn.edu.mnnu.ams.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import cn.edu.mnnu.ams.model.AdminUser;
import cn.edu.mnnu.ams.model.AlumniInfos;

@Controller
@RequestMapping("/Admin")
public class AdminController extends SuperController{

	public AdminController(){
		System.out.println("AdminController init success.");
	}
	/**
	 * 管理员主页 验证session的role元素，过滤用户输入，无则跳转到登陆 有则验证session的pwd元素，检验用户密码修改
	 * 验证成功从DB获取该用户所有信息，显示主页面 验证失败跳转到登陆
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping({ "/index", "" })
	public String index(HttpSession session, HttpServletRequest request, Model m) {
		Object roletype = session.getAttribute("roletype");
		if (roletype == null || !roletype.toString().equals("admin")) return "redirect:/Public/login";
		AdminUser au = adminDAO.getAllInfo(session.getAttribute("uid")
				.toString());
		if(au==null) return "redirect:/Public/login";
		m.addAttribute("role", au.getRole());
		m.addAttribute("name", au.getName());
		return "/Admin/index";
	}

	/**
	 * 更改密码方法 检查新密码合法性(未完成) 验证旧密码 检验成功，更改密码，更新session的pwd元素
	 * 
	 * @param session
	 * @param request
	 * @param oldpwd
	 * @param newpwd
	 * @return
	 */
	@RequestMapping("/updatePwd")
	public String updatePwd(HttpSession session, HttpServletRequest request,
			@RequestParam("oldpwd") String oldpwd,
			@RequestParam("newpwd") String newpwd) {
		String uid = (String) session.getAttribute("uid");
		String pwd = adminDAO.getPassword(uid);
		if (pwd.equals(oldpwd)) {
			logger.debug("password right");
			adminDAO.setPassword(uid, newpwd);
			return "redirect:/Admin/index";
		}
		logger.debug("wrong password");
		return null;
	}

	/**
	 * 显示导入excel页面
	 * 
	 * @return
	 */
	@RequestMapping("/ExcelToMysql")
	public String excelToMysql() {
		return "/Admin/ExcelToMysql";
	}

	/**
	 * 导入excel处理 从request中读取上传文件file，获取输入流stream 判断上传文件类型，是*.xls则赋到Workbook对象
	 * 对.xls进行处理 （未完成）
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/ExcelToMysql", method = RequestMethod.POST)
	public String excelToMysql(@RequestParam("excelFile") MultipartFile file)
			throws IOException {
		InputStream stream = file.getInputStream();
		HSSFWorkbook wb = null;
		if (file.getContentType().equals("application/vnd.ms-excel")) {
			wb = new HSSFWorkbook(stream);
			stream.close();
		} else {
			System.out.println("目前只支持使用*.xls文件");
			return null;
		}
		HSSFSheet s1 = wb.getSheetAt(0);
		String value = "";
		List<AlumniInfos> list = new ArrayList<AlumniInfos>();
		// 处理表头
		HSSFRow row = s1.getRow(0);
		int[] arr = new int[29];
		if (row == null) {
			wb.close();
			return null;
		}
		for (int celli = 0; celli < row.getLastCellNum(); celli++) {
			HSSFCell cell = row.getCell(celli);
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
			else if (headString.equals("工作省份")) arr[celli] = 11;
			else if (headString.equals("工作市")) arr[celli] = 12;
			else if (headString.equals("工作地区")) arr[celli] = 13;
			else if (headString.equals("工作单位")) arr[celli] = 14;
			else if (headString.equals("职务")) arr[celli] = 15;
			else if (headString.equals("职称")) arr[celli] = 16;
			else if (headString.equals("行业")) arr[celli] = 17;
			else if (headString.equals("联系电话")) arr[celli] = 18;
			else if (headString.equals("固定电话")) arr[celli] = 19;
			else if (headString.equals("邮箱")) arr[celli] = 20;
			else if (headString.equals("QQ号")) arr[celli] = 21;
			else if (headString.equals("通讯地址")) arr[celli] = 22;
			else if (headString.equals("邮编")) arr[celli] = 23;
			else if (headString.equals("备注")) arr[celli] = 24;
			else if (headString.equals("所属校友总会")) arr[celli] = 25;
			else if (headString.equals("校友总会职务")) arr[celli] = 26;
			else if (headString.equals("所属校友分会")) arr[celli] = 27;
			else if (headString.equals("校友分会职务")) arr[celli] = 28;
		}
		// 处理表格
		for (int rowi = 1; rowi <= s1.getLastRowNum(); rowi++) {
			row = s1.getRow(rowi);
			if (row == null) continue;
			AlumniInfos ai = new AlumniInfos();
			for (int celli = 0; celli < row.getLastCellNum(); celli++) {
				HSSFCell cell = row.getCell(celli);
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
			// 在此编写对该对象进行处理
			list.add(ai);
		}
		wb.close();
		adminDAO.importAlumniInfos(list);
		return "redirect:/Admin/ExcelToMysql";
	}

	@RequestMapping("/myProfile")
	public String myProfile(HttpSession session, Model m) {
		String roletype = session.getAttribute("roletype").toString();
		if (roletype == null || !roletype.toString().equals("admin")) return "redirect:/Public/login";
		AdminUser au = adminDAO.getAllInfo(session.getAttribute("uid")
				.toString());
		m.addAttribute("role", au.getRole());
		m.addAttribute("auth", au.getAuth());
		m.addAttribute("name", au.getName());
		return "/Admin/myProfile";
	}
}
