package cn.edu.mnnu.ams.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.NDC;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping({ "Public", "" })
public class PublicController extends SuperController {

	public PublicController() {
		System.out.println("PublicController init success.");
	}

	// 显示登陆页
	@RequestMapping({ "/login", "" })
	public String login(HttpSession session) {
		session.invalidate();
		return "/Public/login";
	}

	// 登陆
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginP(HttpSession session, HttpServletRequest request,
			@RequestParam("uid") String uid, @RequestParam("pwd") String pwd) {
		String remoteAddr = request.getRemoteAddr();
		NDC.clear();
		NDC.push(remoteAddr);

		if (uid != null && pwd != null) {
			int res = amsService.Login(uid, pwd);
			if (res == 1) {
				logger.info("Admin: [" + uid + "]<" + pwd + "> logined.");
				session.setAttribute("uid", uid);
				session.setAttribute("roletype", "admin");
				return "redirect:/Admin/index";
			}
			/*
			 * pwd_temp=userDAO.getPassword(uid); if(pwd.equals(pwd_temp)){
			 * logger.info("User: ["+uid+"]<"+pwd+"> logined.");
			 * session.setAttribute("uid", uid);
			 * session.setAttribute("roletype", "user"); return
			 * "redirect:/User/index"; }
			 */
		}
		return "/Public/login";
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/Public/login";
	}

}
