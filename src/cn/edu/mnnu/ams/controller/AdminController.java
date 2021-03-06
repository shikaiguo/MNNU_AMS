package cn.edu.mnnu.ams.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.edu.mnnu.ams.entity.AlumniInfos;
import cn.edu.mnnu.ams.entity.Role;
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
	@RequestMapping({"conditionTemplate", "ExamineVerify" })
	public void justShow(HttpSession session) {}

	// ***************************主页*************************

	@RequestMapping({ "/index", "" })
	public String index(HttpSession session, HttpServletRequest request) {
		if (!session.isNew()) {
			if (amsService.GetSessionRoleType(session) == 1) {
				request.setAttribute("title", "校友信息管理系统 - 闽南师范大学");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				User user=(User) session.getAttribute("user");
				if(user!=null){
			        request.setAttribute("lastlogintime", sdf.format(Long.parseLong(user.getLastlogintime())));  
					return "/Admin/index";
				}
			}
		}
		return "/forbiden";
	}

	// *********个人信息**********
	@RequestMapping("myProfile")
	public String myProfile(HttpSession session, HttpServletRequest request) {
		if (amsService.GetSessionRoleType(session) == 1) {
			//User user = (User) session.getAttribute("user");
			//request.setAttribute("user", user);
			return "/Admin/myProfile";
		}
		return "/forbiden";
	}

	// *****************************用户管理*******************************
	@RequestMapping("sAdminM")
	public String sadminM(HttpSession session,Model m){
		if(!session.isNew()){
			Role role=(Role) session.getAttribute("role");
			if(amsService.GetSessionRoleType(session)==1&&role.getRolename().equals("超级管理员")){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				List<User> list = amsService.getUserList(2);
				for(int i=0;i<list.size();i++){
					User u=list.get(i);
					u.setLastlogintime(sdf.format(Long.parseLong(u.getLastlogintime())));
					list.set(i, u);
				}
				m.addAttribute("list", list);
				m.addAttribute("m_type","子管理员");
				return "Admin/userM";
			}
		}
		return "/forbiden";
	}
	@RequestMapping("userM")
	public String userM(HttpSession session,HttpServletRequest request,Model m){
		if(amsService.GetSessionRoleType(session)==1){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			List<User> list = amsService.getUserList(3);
			for(int i=0;i<list.size();i++){
				User u=list.get(i);
				u.setLastlogintime(sdf.format(Long.parseLong(u.getLastlogintime())));
				list.set(i, u);
			}
			m.addAttribute("list", list);
			m.addAttribute("m_type","用户");
			return "Admin/userM";
		}
		return "/forbiden";
	}
	@RequestMapping("addUser")
	public @ResponseBody String addUser(HttpSession session, HttpServletRequest request, @RequestParam("username") String username, @RequestParam("password") String password,
			@RequestParam("repassword") String repassword, @RequestParam("email") String email, @RequestParam("aid") String aid) {
		if(username.equals("")) return "-1";
		if(password.equals("")||!password.equals(repassword)||password.length()<6) return "-2";
		int bind_id=-1;
		if(!aid.equals(""))bind_id=Integer.parseInt(aid);
		int status=amsService.addUser(username,password,email,bind_id);
		return status+"";
	}
	@RequestMapping("authorityAssign")
	public String authorityAssign(HttpSession session,HttpServletRequest request){
		if(amsService.GetSessionRoleType(session)==1){
			return "Admin/authorityAssign";
		}
		return "/forbiden";
	}
	// *****************************校友信息管理**************************
	// 显示
	@RequestMapping("query")
	public String query(HttpSession session,HttpServletRequest request) {
		if (amsService.GetSessionRoleType(session) == 1) { 
			request.setAttribute("title", "信息查询");
			return "/Admin/query"; }
		return "/forbiden";
	}

	// 进行查询
	@RequestMapping(value = "query", method = RequestMethod.POST)
	public @ResponseBody
	JqGridData<AlumniInfos> loadList(@RequestParam("rows") String rows,
			@RequestParam("page") String page, @RequestParam("val") String condition,HttpServletRequest request, HttpSession session) {
		if (amsService.GetSessionRoleType(session) != 1) return null;
		return amsService.queryAlumniInfos(condition);
	}
	//显示导入页
	@RequestMapping("importExcel")
	public String importExcel(HttpSession session) {
		if (amsService.GetSessionRoleType(session) != 1) return "/forbiden";
		return "/Admin/importExcel";
	}
	//导入操作
	@RequestMapping(value = "/ExcelToMysql", method = RequestMethod.POST)
	public @ResponseBody
	List<AlumniInfos> excelToMysql(
			@RequestParam("excelFile") MultipartFile file, HttpSession session)
			throws IOException {
		if (amsService.GetSessionRoleType(session) != 1) return null;
		List<AlumniInfos> list = amsService.readExcel(file);
		amsService.importExcel(list);
		return list;
	}
	// 显示维护页面
	@RequestMapping("maintain")
	public String maintain(HttpSession session,HttpServletRequest request) {
		if (amsService.GetSessionRoleType(session) != 1) return "/forbiden";
		request.setAttribute("title", "信息维护");
		return "Admin/maintain";
	}
	//显示审批页面
	@RequestMapping("examineVerify")
	public String examineVerify(HttpSession session, Model m) {
		if (amsService.GetSessionRoleType(session) != 1) return "/forbiden";
		List<ExamineInfo> list = amsService.getExamineInfos();
		m.addAttribute("list", list);
		return "Admin/examineVerify";
	}
	//显示简单个人信息
	@RequestMapping("simpleUserInfo/{uid}")
	public String simpleUserInfo(@PathVariable String uid, HttpSession session,
			Model m) {
		if (amsService.GetSessionRoleType(session) != 1) return null;
		m.addAttribute("puid", uid);
		/* User u=amsService.getUser(uid); */
		return "Admin/simpleUserInfo";
	}
	//通过审批
	@RequestMapping(value = "passEV", method = RequestMethod.POST)
	public @ResponseBody
	String passEV(@RequestParam("arr") String arr, HttpSession session) {
		if (amsService.GetSessionRoleType(session) != 1) return "无效用户";
		amsService.passEV(arr.split(","));
		return "xxx";
	}
	//拒绝修改
	@RequestMapping(value = "refuseEV", method = RequestMethod.POST)
	public @ResponseBody
	String refuseEV(@RequestParam("arr") String arr, HttpSession session) {
		if (amsService.GetSessionRoleType(session) != 1) return "无效用户";
		amsService.refuseEV(arr.split(","));
		return "xxx";
	}
	//清空冗余
	@RequestMapping(value = "flushEV", method = RequestMethod.POST)
	public @ResponseBody
	String flushEV(HttpSession session) {
		if (amsService.GetSessionRoleType(session) != 1) return "无效用户";
		List<ExamineInfo> list = amsService.getExamineInfos();
		amsService.flushEV(list);
		return "xxx";
	}

	// *****************************校友信息统计**************************
	@RequestMapping(value = { "/alumniStatistics", "/statistics" }, method = RequestMethod.GET)
	public String toAlumniStatistics(HttpSession session,HttpServletRequest request) {
		if (amsService.GetSessionRoleType(session) != 1) return "/forbiden";
		request.setAttribute("title", "信息统计");
		return "/Admin/statistics";
	}

	@RequestMapping(value = "/getFromStatisticsAll", method = RequestMethod.POST)
	public @ResponseBody
	List<EchartData> getFromStatisticsAll(HttpSession session) {
		if (amsService.GetSessionRoleType(session) != 1) return null;
		return amsService.getAreaStatistics("AlumniFrom");
	}

	@RequestMapping(value = "/getWorkStatisticsAll", method = RequestMethod.POST)
	public @ResponseBody
	List<EchartData> getWorkStatisticsAll(HttpSession session) {
		if (amsService.GetSessionRoleType(session) != 1) return null;
		return amsService.getAreaStatistics("AlumniWork");
	}

	@RequestMapping(value = "getProfessionType/{dept}", method = RequestMethod.POST)
	public @ResponseBody
	List<String> getProfessionType(HttpSession session, @PathVariable String dept) {
		if (amsService.GetSessionRoleType(session) != 1) return null;
		return amsService.getProfessionType(dept);
	}

	@RequestMapping(value = "getProfessionStatistics/{dept}", method = RequestMethod.POST)
	public @ResponseBody
	List<EchartData> getProfessionStatistics(HttpSession session,
			@PathVariable String dept) {
		if (amsService.GetSessionRoleType(session) != 1) return null;
		return amsService.getProfessionStatistics(dept);
	}

	@RequestMapping(value = "getDeptStatistics", method = RequestMethod.POST)
	public @ResponseBody
	List<EchartData> getDeptStatistics(HttpSession session) {
		if (amsService.GetSessionRoleType(session) != 1) return null;
		return amsService.getDeptStatistics();
	}

	// *****************************校友园地*******************************
	// *****************************系统维护*******************************
	@RequestMapping("deptSetting")
	public String toDeptSetting(HttpSession session,HttpServletRequest request) {
		if (amsService.GetSessionRoleType(session) != 1) return "/forbiden";
		request.setAttribute("title", "院系设置");
		request.setAttribute("list", amsService.getDept());
		return "/Admin/deptSetting";
	}

	@RequestMapping(value = "addNewDept", method = RequestMethod.POST)
	public @ResponseBody
	String addNewDept(@RequestParam("id") String id,
			@RequestParam("name") String name, HttpSession session) {
		if (amsService.GetSessionRoleType(session) != 1) return "/forbiden";
		amsService.addDept(id, name);
		return "1";

	}

}
