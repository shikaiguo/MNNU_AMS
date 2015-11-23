package cn.edu.mnnu.ams.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.mnnu.ams.entity.User;

@Controller
@RequestMapping({"User"})
public class UserController  extends SuperController {
	public UserController() {
		System.out.println("AdminController init success.");
	}
	// ***************************主页*************************

		@RequestMapping({ "/index","" })
		public String index(HttpSession session, HttpServletRequest request) {
			if (!session.isNew()) {
				if (amsService.GetSessionRoleType(session) == 2) {
					request.setAttribute("title", "校友信息管理系统 - 闽南师范大学");
					return "/User/index";
				}
			}
			return "/forbiden";
		}
		@RequestMapping("myProfile")
		public String myProfile(HttpSession session,HttpServletRequest request){
			if (amsService.GetSessionRoleType(session) == 2) {
				User user=amsService.getUser(session.getAttribute("username").toString());
				int bindid=user.getBindid();
				if(bindid!=-1){
					request.setAttribute("ai", amsService.getAlumniInfoFromId(user.getBindid()));
				}
				return "/User/myProfile";
			}
			return "forbiden";
		}
		@RequestMapping(value="updateProfile",method=RequestMethod.POST)
		public @ResponseBody String updateProfile(HttpSession session){
			if (amsService.GetSessionRoleType(session) == 2) {
				return "/User/myProfile";
			}
			return "forbiden";
		}
}
