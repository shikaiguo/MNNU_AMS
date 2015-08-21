package cn.edu.mnnu.ams.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.edu.mnnu.ams.model.AdminUser;
import cn.edu.mnnu.ams.model.AlumniInfos;
import cn.edu.mnnu.ams.model.JqGridData;

@Controller
@RequestMapping("/Admin")
public class AdminController extends SuperController {

	public AdminController() {
		System.out.println("AdminController init success.");
	}

	// -----------------------------------------------公共---------------------------------------------------
	public boolean verify(HttpSession session) {
		if (session.isNew()) return false;
		Object roletype = session.getAttribute("roletype");
		if (roletype == null || !roletype.toString().equals("admin")) return false;
		return true;
	}

	@RequestMapping({ "header", "footer", "import", "query",
			"conditionTemplate" })
	public void justShow() {}

	// -----------------------------------------------入口/主页------------------------------------------------------------------------
	/*
	 * 管理员主页 验证session的role元素，过滤用户输入，无则跳转到登陆 有则验证session的pwd元素，检验用户密码修改
	 * 验证成功从DB获取该用户所有信息，显示主页面 验证失败跳转到登陆
	 */
	@RequestMapping({ "/index", "" })
	public String index(HttpSession session, HttpServletRequest request) {
		if (verify(session) == false) return "redirect:/Public/login";
		AdminUser au = adminDAO.getAllInfo(session.getAttribute("uid")
				.toString());
		if (au == null) return "redirect:/Public/login";
		session.setAttribute("role", au.getRole());
		session.setAttribute("name", au.getName());
		return "/Admin/index";
	}

	// -----------------------------------------------个人信息---------------------------------------------------------------------------

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

	/*
	 * 更改密码方法 检查新密码合法性(未完成) 验证旧密码 检验成功，更改密码，更新session的pwd元素
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

	// -----------------------------------------------用户管理-------------------------------------------------------------------------

	// 权限分配页面
	@RequestMapping(value = "/authorityAssign", method = RequestMethod.GET)
	public String toAuthorityAssign(HttpSession session) {
		if (verify(session) == false) return "redirect:/Public/login";
		return "/Admin/authorityAssign";
	}

	// -----------------------------------------------校友信息管理--------------------------------------------------------------------------
	/*
	 * 显示导入excel页面
	 */
	@RequestMapping("/ExcelToMysql")
	public String excelToMysql() {
		return "/Admin/ExcelToMysql";
	}

	/*
	 * 导入excel处理 从request中读取上传文件file，获取输入流stream 判断上传文件类型，是*.xls则赋到Workbook对象
	 * 对.xls进行处理 （未完成）
	 */
	@RequestMapping(value = "/ExcelToMysql", method = RequestMethod.POST)
	public @ResponseBody
	String excelToMysql(@RequestParam("excelFile") MultipartFile file,
			HttpServletResponse response) throws IOException {
		InputStream stream = file.getInputStream();
		HSSFWorkbook wb = null;
		if (file.getContentType().equals("application/vnd.ms-excel")) {
			wb = new HSSFWorkbook(stream);
			stream.close();
		} else {
			return "目前只支持使用*.xls文件";
		}
		HSSFSheet s1 = wb.getSheetAt(0);
		String value = "";
		List<AlumniInfos> list = new ArrayList<AlumniInfos>();
		// 处理表头
		HSSFRow row = s1.getRow(0);
		int[] arr = new int[29];
		if (row == null) {
			wb.close();
			return "表格内容出错，请先检查";
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
		System.out.println(list.size());
		// adminDAO.importAlumniInfos(list);
		return "上传成功";
	}

	// 查询页面
	@RequestMapping(value = "/query2", method = RequestMethod.GET)
	public String toQuery() {
		return "/Admin/query2";
	}

	// 查询对话框
	@RequestMapping(value = "/queryDialog")
	public String toQueryDialog() {
		return "/Admin/queryDialog";
	}

	// jqgrid 读取数据
	@RequestMapping(value = "/jqgridAllData")
	public @ResponseBody
	JqGridData<AlumniInfos> loadList(@RequestParam("rows") String rows,
			@RequestParam("page") String page, @RequestParam("val") String val,
			AlumniInfos user, HttpServletRequest request) {
		JqGridData<AlumniInfos> ulist = new JqGridData<AlumniInfos>();
		List<AlumniInfos> alumniList = new ArrayList<AlumniInfos>();
		int rowsInt = Integer.parseInt(rows);
		int pageInt = Integer.parseInt(page);
		ulist.setPage(pageInt);
		ulist.setRows(rowsInt);
		if(val==null)
			return ulist;
		System.out.println(val);
		if(val.equals("all"))val="1=1";
		else if(val.lastIndexOf("&")==val.length()-1)val=val.substring(0,val.length()-1);
		val = val == null ? "1=1" : val.replace("&", " AND ");
		alumniList = adminDAO.queryAlumniInfos(val);
		if (alumniList != null) {
			for (int i = 0; i < alumniList.size(); i++) {
				System.out.println(alumniList.get(i).getSname());
			}
		}
		int totalRecord = alumniList.size();
		ulist.setRecords(totalRecord);
		int totalPage = totalRecord % rowsInt == 0 ? totalRecord / rowsInt
				: totalRecord / rowsInt + 1;
		ulist.setTotal(totalPage);
		ulist.setGridModel(alumniList);
		return ulist;
	}

	// 字段显示选择页面
	@RequestMapping(value = "/selectFilter", method = RequestMethod.GET)
	public String toSelectFilter() {
		return "/Admin/selectFilter";
	}

	@RequestMapping(value = "/saveEdit")
	public @ResponseBody
	JqGridData<AlumniInfos> getSendData(@RequestParam("somename") String s) {
		userDAO.saveEdit(s);
		return null;
	}

	// 邮件管理
	@RequestMapping(value = "/emailsMenage")
	public String toEmailMenage() {
		return "/Admin/emailsMenage";
	}

	// 发送邮件
	@RequestMapping(value = "/sendEmails", method = RequestMethod.GET)
	public String toSendEmails() {
		return "/Admin/sendEmails";
	}

	// 定期发送邮件
	@RequestMapping(value = "/sendRegular", method = RequestMethod.GET)
	public String toSendRegular() {
		return "/Admin/sendRegular";
	}

	// -----------------------------------------------校友统计-------------------------------------------------------------------------------
	// 校友统计页面
	@RequestMapping(value = "/alumniStatistics", method = RequestMethod.GET)
	public String toAlumniStatistics() {
		return "/Admin/alumniStatistics";
	}
	// -----------------------------------------------校友园地--------------------------------------------------------------------------------
	// -----------------------------------------------系统维护--------------------------------------------------------------------------------
}
