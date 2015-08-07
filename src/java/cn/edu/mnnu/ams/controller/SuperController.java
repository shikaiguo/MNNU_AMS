package cn.edu.mnnu.ams.controller;

import org.apache.log4j.Logger;

import cn.edu.mnnu.ams.DAO.AdminDAO;
import cn.edu.mnnu.ams.DAO.UserDAO;

public class SuperController {
	protected static Logger logger = Logger.getLogger(AdminController.class);
	protected static AdminDAO adminDAO=new AdminDAO();
	protected static UserDAO userDAO=new UserDAO();
}
