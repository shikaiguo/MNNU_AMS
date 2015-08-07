package cn.edu.mnnu.ams.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.mnnu.ams.Func.Func;
import cn.edu.mnnu.ams.model.AlumniInfos;
import cn.edu.mnnu.ams.model.User;

@Controller
@RequestMapping("/User")
public class UserController extends SuperController{

	public UserController(){
		System.out.println("UserController init success.");
	}
	
	@RequestMapping({ "/index", "" })
	public String index(HttpSession session, HttpServletRequest request,Model m) {
		Object roletype = session.getAttribute("roletype");
		if (roletype == null || !roletype.toString().equals("user")) return "redirect:/Public/login";

		User u = userDAO.getUserInfo(session.getAttribute("uid").toString());
		if(u==null) return "redirect:/Public/login";
		session.setAttribute("uid", u.getUid());
		String role=new Func().getRole(u.getRole());
		m.addAttribute("role",role);
		if(!role.equals("校友")){
			m.addAttribute("name", u.getUid());
		}
		else{
			if(u.getBindid()!=null)
				m.addAttribute("name",userDAO.getDetailById(u.getBindid()).getSname());
			else
				m.addAttribute("name",u.getUid());
		}
		return "/User/index";
	}
	@RequestMapping("/personInfo")
	public String personInfo(){
		return "/User/personInfo";
	}
	@RequestMapping("/Query")
	public void query(){
	}
	@RequestMapping(value="/QueryShow",method=RequestMethod.POST)
	public @ResponseBody List<AlumniInfos> queryP(@RequestParam("condition")String condition){
		/*
		 * 判断角色   根据角色+限制字段   校长+""   院长+"And dept=xxx"
		 * 0@all    1@8    2@120  3@6
		 */
		List<AlumniInfos>list=userDAO.getAllInfos(condition);
		return list;
	}
	@RequestMapping({"/allumniGarden","personInfo","Query","QueryShow"})
	public void show(){}
}
