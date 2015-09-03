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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.edu.mnnu.ams.entity.Admin;
import cn.edu.mnnu.ams.entity.AlumniInfos;
import cn.edu.mnnu.ams.entity.User;
import cn.edu.mnnu.ams.model.EchartData;
import cn.edu.mnnu.ams.model.ExamineInfo;
import cn.edu.mnnu.ams.model.JqGridData;

@Controller
@RequestMapping("Admin")
public class AdminController extends SuperController {

	public AdminController() {
		System.out.println("AdminController init success.");
	}

	// ***************************公共**************************
	public boolean verify(HttpSession session) {
		if (session.isNew()) return false;
		logger.info("asdfasdfasf");
		Object roletype = session.getAttribute("roletype");
		if (roletype == null || !roletype.toString().equals("admin")) return false;
		return true;
	}

	@RequestMapping({ "header", "footer", "conditionTemplate",
			"ExamineVerify"})
	public void justShow(HttpSession session) {}

	// ***************************主页*************************

	@RequestMapping({ "/index", "" })
	public String index(HttpSession session, HttpServletRequest request) {
		if (verify(session) == false) return "redirect:/Public/login";
		Admin au = amsService.getAdminUser(session.getAttribute("uid")
				.toString());
		if (au == null) return "redirect:/Public/login";
		session.setAttribute("role", au.getRole());
		session.setAttribute("name", au.getName());
		return "/Admin/index";
	}

	// *********个人信息**********
	@RequestMapping("myProfile")
	public String myProfile(HttpSession session, Model m) {
		if (verify(session) == false) return "redirect:/Public/login";
		String roletype = session.getAttribute("roletype").toString();
		if (roletype == null || !roletype.toString().equals("admin")) return "redirect:/Public/login";
		Admin au = amsService.getAdminUser(session.getAttribute("uid")
				.toString());
		m.addAttribute("role", au.getRole());
		m.addAttribute("auth", au.getAuth());
		m.addAttribute("name", au.getName());
		return "/Admin/myProfile";
	}

	// *****************************用户管理*******************************
	// *****************************校友信息管理**************************
	// 显示
	@RequestMapping("query")
	public String query(HttpSession session) {
		if (verify(session) == false) return "redirect:/Public/login";
		return "/Admin/query";
	}

	// 进行查询
	@RequestMapping(value = "query",method=RequestMethod.POST)
	public @ResponseBody
	JqGridData<AlumniInfos> loadList(@RequestParam("rows") String rows,
			@RequestParam("page") String page, @RequestParam("val") String val,
			AlumniInfos user, HttpServletRequest request, HttpSession session) {
		if (verify(session) == false) return null;
		JqGridData<AlumniInfos> ulist = new JqGridData<AlumniInfos>();
		List<AlumniInfos> alumniList = new ArrayList<AlumniInfos>();
		int rowsInt = Integer.parseInt(rows);
		int pageInt = Integer.parseInt(page);
		ulist.setPage(pageInt);
		ulist.setRows(rowsInt);
		if (val == null) return ulist;
		alumniList = amsService.getAlumniInfo(val);
		int totalRecord = alumniList.size();
		ulist.setRecords(totalRecord);
		int totalPage = totalRecord % rowsInt == 0 ? totalRecord / rowsInt
				: totalRecord / rowsInt + 1;
		ulist.setTotal(totalPage);
		ulist.setGridModel(alumniList);
		return ulist;
	}

	@RequestMapping("importExcel")
	public String importExcel(HttpSession session) {
		if (verify(session) == false) return "redirect:/Public/login";
		return "/Admin/importExcel";
	}
	@RequestMapping(value = "/ExcelToMysql", method = RequestMethod.POST)
	public @ResponseBody
	List<AlumniInfos> excelToMysql(@RequestParam("excelFile") MultipartFile file,
			HttpServletResponse response,HttpSession session) throws IOException {

		if (verify(session) == false) return null;//"redirect:/Public/login";
		InputStream stream = file.getInputStream();
		HSSFWorkbook wb = null;
		if (file.getContentType().equals("application/vnd.ms-excel")) {
			wb = new HSSFWorkbook(stream);
			stream.close();
		} else {
			return null;//"目前只支持使用*.xls文件";
		}
		HSSFSheet s1 = wb.getSheetAt(0);
		String value = "";
		List<AlumniInfos> list = new ArrayList<AlumniInfos>();
		// 处理表头
		HSSFRow row = s1.getRow(0);
		int[] arr = new int[29];
		if (row == null) {
			wb.close();
			return null;//"表格内容出错，请先检查";
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
					case 0:ai.setDept(value);break;
					case 1:ai.setMajor(value);break;
					case 2:ai.setCls(value);break;
					case 3:ai.setSno(value);break;
					case 4:ai.setSname(value);break;
					case 5:ai.setSex(value);break;
					case 6:ai.setProvincefrom(value);break;
					case 7:ai.setCityfrom(value);break;
					case 8:ai.setDistrictfrom(value);break;
					case 9:ai.setDegree(value);break;
					case 10:ai.setGratime(value);break;
					case 11:ai.setProvincework(value);break;
					case 12:ai.setCitywork(value);break;
					case 13:ai.setDistrictwork(value);break;
					case 14:ai.setWorkunit(value);break;
					case 15:ai.setDuty(value);break;
					case 16:ai.setJob(value);break;
					case 17:ai.setProfession(value);break;
					case 18:ai.setPhone1(value);break;
					case 19:ai.setPhone2(value);break;
					case 20:ai.setMail(value);break;
					case 21:ai.setQq(value);break;
					case 22:ai.setAddress(value);break;
					case 23:ai.setZip(value);break;
					case 24:ai.setNote(value);break;
					case 25:ai.setAssoc1(value);break;
					case 26:ai.setAssoc1job(value);break;
					case 27:ai.setAssoc2(value);break;
					case 28:ai.setAssoc2job(value);break;
				}
			}
			// 在此编写对该对象进行处理
			list.add(ai);
		}
		wb.close();
		logger.info("上传excel");
		return list;// "上传成功";
	}
	//审批
	@RequestMapping("maintain")
	public String maintain(HttpSession session){
		if (verify(session) == false) return "redirect:/Public/login";
		return "Admin/maintain";
	}
	@RequestMapping("examineVerify")
	public String examineVerify(HttpSession session,Model m){
		if (verify(session) == false) return "redirect:/Public/login";
		List<ExamineInfo> list=amsService.getExamineInfo();
		m.addAttribute("list", list);
		return "Admin/examineVerify";
	}
	@RequestMapping("simpleUserInfo/{uid}")
	public String simpleUserInfo(@PathVariable String uid,HttpSession session,Model m){
		if (verify(session) == false) return null;
		m.addAttribute("puid", uid);
		User u=amsService.getUser(uid);
		if(!u.getBindid().equals("")){
			AlumniInfos ai=(AlumniInfos) amsService.getAlumniInfoItem(u.getBindid());
			m.addAttribute("pname",ai.getSname());
			m.addAttribute("pdept", ai.getDept());
			m.addAttribute("pmajor", ai.getMajor());
			m.addAttribute("psno", ai.getSno());
		}
		return "Admin/simpleUserInfo";
	}
	@RequestMapping(value="passEV",method=RequestMethod.POST)
	public @ResponseBody String passEV(@RequestParam("arr") String arr,HttpSession session){
		if (verify(session) == false) return "无效用户";
		amsService.passEV(arr.split(","));
		return "xxx";
	}
	@RequestMapping(value="refuseEV",method=RequestMethod.POST)
	public @ResponseBody String refuseEV(@RequestParam("arr") String arr,HttpSession session){
		if (verify(session) == false) return "无效用户";
		amsService.refuseEV(arr.split(","));
		return "xxx";
	}
	@RequestMapping(value="flushEV",method=RequestMethod.POST)
	public @ResponseBody String flushEV(HttpSession session){
		if (verify(session) == false) return "无效用户";
		List<ExamineInfo> list=amsService.getExamineInfo();
		amsService.flushEV(list);
		return "xxx";
	}
	// *****************************校友信息统计**************************
	@RequestMapping(value = { "/alumniStatistics", "/statistics" }, method = RequestMethod.GET)
	public String toAlumniStatistics(HttpSession session) {
		if (verify(session) == false) return "redirect:/Public/login";
		//System.out.println(adminDAO.getFromStatisticsAll());
		return "/Admin/statistics";
	}

	@RequestMapping(value = "/getFromStatisticsAll", method = RequestMethod.POST)
	public @ResponseBody List<EchartData> getFromStatisticsAll(HttpSession session) {
		if (verify(session) == false) return null;
		return amsService.getAreaStatistics("AlumniFrom");
	}
	@RequestMapping(value = "/getWorkStatisticsAll", method = RequestMethod.POST)
	public @ResponseBody List<EchartData> getWorkStatisticsAll(HttpSession session) {
		if (verify(session) == false) return null;
		return amsService.getAreaStatistics("AlumniWork");
	}
	@RequestMapping(value = "getJobType/{dept}", method = RequestMethod.POST)
	public @ResponseBody List<String> getJobType(HttpSession session,@PathVariable String dept) {
		if (verify(session) == false) return null;
		return amsService.getJobType(dept);
	}
	@RequestMapping(value = "getJobStatistics/{dept}", method = RequestMethod.POST)
	public @ResponseBody List<EchartData> getJobStatistics(HttpSession session,@PathVariable String dept) {
		if (verify(session) == false) return null;
		return amsService.getJobStatistics(dept);
	}
	@RequestMapping(value = "getDeptStatistics", method = RequestMethod.POST)
	public @ResponseBody List<EchartData> getDeptStatistics(HttpSession session) {
		if (verify(session) == false) return null;
		return amsService.getDeptStatistics();
	}
	// *****************************校友园地*******************************
	// *****************************系统维护*******************************
	@RequestMapping("deptSetting")
	public String toDeptSetting(Model m,HttpSession session) {
		if (verify(session) == false) return "redirect:/Public/login";
		m.addAttribute("list", amsService.getDept());
		return "/Admin/deptSetting";
	}
	@RequestMapping(value="addNewDept",method=RequestMethod.POST)
	public @ResponseBody String addNewDept(@RequestParam("id") String id,@RequestParam("name") String name,HttpSession session){
		if (verify(session) == false) return "redirect:/Public/login";
		amsService.addDept(id,name);
		return "1";

	}

}
