package cn.edu.mnnu.ams.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.NDC;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({ "Public",""})
public class PublicController extends SuperController {

	public PublicController() {
		System.out.println("PublicController init success.");
	}

	@RequestMapping("updatePwd")
	public String updatePwd(HttpSession session){
		if(amsService.VerifyUser(session)==0)return "forbiden";
		return "Public/updatePwd";
	}
	@RequestMapping(value="updatePwd",method=RequestMethod.POST)
	public @ResponseBody int updatePwdP(HttpSession session,@RequestParam("opwd")String opwd,@RequestParam("npwd")String npwd){
		if(amsService.VerifyUser(session)!=0&&opwd!=null&&npwd!=null){
			return amsService.updatePassword(session.getAttribute("username").toString(),opwd,npwd);
		}
		return 0;
	}
	// 显示登陆页
	@RequestMapping({ "/login", "" })
	public String login(HttpSession session) {
		session.invalidate();
		return "/Public/login";
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		return "redirect:/Public/login";
	}

	// 登陆
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginP(HttpSession session, HttpServletRequest request,
			@RequestParam("uid") String username,
			@RequestParam("pwd") String password) {
		String remoteAddr = request.getRemoteAddr();
		NDC.clear();
		NDC.push(remoteAddr);

		if (username != null && password != null) {
			int res = amsService.Login(username, password);
			switch (res) {
				case 1:
					logger.info("Admin: [" + username + "] logined.");
					session.setAttribute("username", username);
					return "redirect:/Admin/index";
				case 2:
					logger.info("User: [" + username + "] logined.");
					session.setAttribute("username", username);
					return "redirect:/User/index";
			}
		}
		return "/Public/login";
	}
	@RequestMapping("forbiden")
	public void forbiden(){}
}
