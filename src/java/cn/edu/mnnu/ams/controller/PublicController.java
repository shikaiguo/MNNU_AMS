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
	 * ��½���� �ж�session�е�roleԪ�أ�����䵱�жϿ���������ת����Ӧ��ҳ ������ʾ��½ҳ��
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
	 * �ύ��½���� �ж��ύ��uid��pwd���жϵ�½�� ��֤�ɹ�����session��uid��pwd��roleԪ�أ���ת����Ӧ��ҳ ��֤ʧ�ܷ��ص�¼ҳ
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
	 * ע������ ע��session �����µ�session�����ص�¼ҳ
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
