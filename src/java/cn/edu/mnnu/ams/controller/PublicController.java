package cn.edu.mnnu.ams.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.NDC;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping({ "/Public", "" })
public class PublicController extends SuperController{

	public PublicController(){
		 System.out.println("PublicController init success.");
	}
	/**
	 * 登陆方法 判断session中的role元素，有则充当判断控制器，跳转到相应主页 无则显示登陆页面
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping({"/login",""})
	public String login(HttpSession session, HttpServletRequest request) {
		String remoteAddr = request.getRemoteAddr();
		NDC.clear();
		NDC.push(remoteAddr);
		
		Object roletype = session.getAttribute("roletype");
		if (roletype != null) {
			if (roletype.toString().equals("admin")) {
				logger.debug("verify session to admin");
				return "redirect:/Admin/index";
			} else if (roletype.toString().equals("user")) {
				logger.debug("verify session to user");
				return "redirect:/User/index";
			}
		}
		
		return "/Public/login";
	}
	/**
	 * 提交登陆方法 判断提交的uid和pwd，判断登陆者 验证成功设置session中uid，pwd，role元素，跳转到对应主页 验证失败返回登录页
	 * 
	 * @param session
	 * @param request
	 * @param uid
	 * @param pwd
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginP(HttpSession session, HttpServletRequest request,
			@RequestParam("uid") String uid, @RequestParam("pwd") String pwd) {
		String remoteAddr = request.getRemoteAddr();
		NDC.clear();
		NDC.push(remoteAddr);

		
		if (uid != null && pwd != null) {
			String pwd_temp = adminDAO.getPassword(uid);
			if (pwd.equals(pwd_temp)) {
				logger.info("Admin: [" + uid + "]<" + pwd + "> logined.");
				session.setAttribute("uid", uid);
				session.setAttribute("roletype", "admin");
				return "redirect:/Admin/index";
			}
			pwd_temp=userDAO.getPassword(uid);
			if(pwd.equals(pwd_temp)){
				logger.info("User: ["+uid+"]<"+pwd+"> logined.");
				session.setAttribute("uid", uid);
				session.setAttribute("roletype", "user");
				return "redirect:/User/index";
			}
		}
		return "/Public/login";
	}

	/**
	 * 注销方法 注销session 生成新的session，返回登录页
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/Public/login";
	}
}
