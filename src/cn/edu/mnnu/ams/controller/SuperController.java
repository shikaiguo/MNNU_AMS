package cn.edu.mnnu.ams.controller;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import cn.edu.mnnu.ams.service.IAmsService;

public class SuperController {
	protected static Logger logger = Logger.getLogger(SuperController.class);

	@Resource(name = "amsService")
	protected IAmsService amsService;
}
