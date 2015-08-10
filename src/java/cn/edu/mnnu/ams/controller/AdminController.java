package cn.edu.mnnu.ams.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import cn.edu.mnnu.ams.model.AdminUser;
import cn.edu.mnnu.ams.model.AlumniInfos;

@Controller
@RequestMapping("/Admin")
public class AdminController extends SuperController{

	public AdminController(){
		System.out.println("AdminController init success.");
	}
	/**
	 * ����Ա��ҳ ��֤session��roleԪ�أ������û����룬������ת����½ ������֤session��pwdԪ�أ������û������޸�
	 * ��֤�ɹ���DB��ȡ���û�������Ϣ����ʾ��ҳ�� ��֤ʧ����ת����½
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping({ "/index", "" })
	public String index(HttpSession session, HttpServletRequest request, Model m) {
		Object roletype = session.getAttribute("roletype");
		if (roletype == null || !roletype.toString().equals("admin")) return "redirect:/Public/login";
		AdminUser au = adminDAO.getAllInfo(session.getAttribute("uid")
				.toString());
		if(au==null) return "redirect:/Public/login";
		m.addAttribute("role", au.getRole());
		m.addAttribute("name", au.getName());
		return "/Admin/index";
	}

	/**
	 * �������뷽�� ���������Ϸ���(δ���) ��֤������ ����ɹ����������룬����session��pwdԪ��
	 * 
	 * @param session
	 * @param request
	 * @param oldpwd
	 * @param newpwd
	 * @return
	 */
	@RequestMapping("/updatePwd")
	public String updatePwd(HttpSession session, HttpServletRequest request,
			@RequestParam("oldpwd") String oldpwd,
			@RequestParam("newpwd") String newpwd) {
		String uid = (String) session.getAttribute("uid");
		String pwd = adminDAO.getPassword(uid);
		if (pwd.equals(oldpwd)) {
			logger.debug("password right");
			adminDAO.setPassword(uid, newpwd);
			return "redirect:/Admin/index";
		}
		logger.debug("wrong password");
		return null;
	}

	/**
	 * ��ʾ����excelҳ��
	 * 
	 * @return
	 */
	@RequestMapping("/ExcelToMysql")
	public String excelToMysql() {
		return "/Admin/ExcelToMysql";
	}

	/**
	 * ����excel���� ��request�ж�ȡ�ϴ��ļ�file����ȡ������stream �ж��ϴ��ļ����ͣ���*.xls�򸳵�Workbook����
	 * ��.xls���д��� ��δ��ɣ�
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/ExcelToMysql", method = RequestMethod.POST)
	public String excelToMysql(@RequestParam("excelFile") MultipartFile file)
			throws IOException {
		InputStream stream = file.getInputStream();
		HSSFWorkbook wb = null;
		if (file.getContentType().equals("application/vnd.ms-excel")) {
			wb = new HSSFWorkbook(stream);
			stream.close();
		} else {
			System.out.println("Ŀǰֻ֧��ʹ��*.xls�ļ�");
			return null;
		}
		HSSFSheet s1 = wb.getSheetAt(0);
		String value = "";
		List<AlumniInfos> list = new ArrayList<AlumniInfos>();
		// �����ͷ
		HSSFRow row = s1.getRow(0);
		int[] arr = new int[29];
		if (row == null) {
			wb.close();
			return null;
		}
		for (int celli = 0; celli < row.getLastCellNum(); celli++) {
			HSSFCell cell = row.getCell(celli);
			if (cell == null) continue;
			String headString = cell.getStringCellValue();
			if (headString.equals("ϵ��")) arr[celli] = 0;
			else if (headString.equals("רҵ")) arr[celli] = 1;
			else if (headString.equals("�༶")) arr[celli] = 2;
			else if (headString.equals("ѧ��")) arr[celli] = 3;
			else if (headString.equals("����")) arr[celli] = 4;
			else if (headString.equals("�Ա�")) arr[celli] = 5;
			else if (headString.equals("��Դ��ʡ")) arr[celli] = 6;
			else if (headString.equals("��Դ����")) arr[celli] = 7;
			else if (headString.equals("��Դ����")) arr[celli] = 8;
			else if (headString.equals("ѧ��")) arr[celli] = 9;
			else if (headString.equals("��ҵʱ��")) arr[celli] = 10;
			else if (headString.equals("����ʡ��")) arr[celli] = 11;
			else if (headString.equals("������")) arr[celli] = 12;
			else if (headString.equals("��������")) arr[celli] = 13;
			else if (headString.equals("������λ")) arr[celli] = 14;
			else if (headString.equals("ְ��")) arr[celli] = 15;
			else if (headString.equals("ְ��")) arr[celli] = 16;
			else if (headString.equals("��ҵ")) arr[celli] = 17;
			else if (headString.equals("��ϵ�绰")) arr[celli] = 18;
			else if (headString.equals("�̶��绰")) arr[celli] = 19;
			else if (headString.equals("����")) arr[celli] = 20;
			else if (headString.equals("QQ��")) arr[celli] = 21;
			else if (headString.equals("ͨѶ��ַ")) arr[celli] = 22;
			else if (headString.equals("�ʱ�")) arr[celli] = 23;
			else if (headString.equals("��ע")) arr[celli] = 24;
			else if (headString.equals("����У���ܻ�")) arr[celli] = 25;
			else if (headString.equals("У���ܻ�ְ��")) arr[celli] = 26;
			else if (headString.equals("����У�ѷֻ�")) arr[celli] = 27;
			else if (headString.equals("У�ѷֻ�ְ��")) arr[celli] = 28;
		}
		// ������
		for (int rowi = 1; rowi <= s1.getLastRowNum(); rowi++) {
			row = s1.getRow(rowi);
			if (row == null) continue;
			AlumniInfos ai = new AlumniInfos();
			for (int celli = 0; celli < row.getLastCellNum(); celli++) {
				HSSFCell cell = row.getCell(celli);
				if (cell == null) continue;
				switch (cell.getCellType()) {
					case HSSFCell.CELL_TYPE_NUMERIC:
						// �����ֽ��и�ʽ����ʹ�䲻�Զ�ת��Ϊ��ѧ��������
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
				// ��ai.setXXX()ǰ��д����Ը����Խ��д���
				switch (arr[celli]) {
					case 0:
						ai.setDept(value);
						break;
					case 1:
						ai.setMajor(value);
						break;
					case 2:
						ai.setCls(value);
						break;
					case 3:
						ai.setSno(value);
						break;
					case 4:
						ai.setSname(value);
						break;
					case 5:
						ai.setSex(value);
						break;
					case 6:
						ai.setProvincefrom(value);
						break;
					case 7:
						ai.setCityfrom(value);
						break;
					case 8:
						ai.setDistrictfrom(value);
						break;
					case 9:
						ai.setDegree(value);
						break;
					case 10:
						ai.setGratime(value);
						break;
					case 11:
						ai.setProvincework(value);
						break;
					case 12:
						ai.setCitywork(value);
						break;
					case 13:
						ai.setDistrictwork(value);
						break;
					case 14:
						ai.setWorkunit(value);
						break;
					case 15:
						ai.setDuty(value);
						break;
					case 16:
						ai.setJob(value);
						break;
					case 17:
						ai.setProfession(value);
						break;
					case 18:
						ai.setPhone1(value);
						break;
					case 19:
						ai.setPhone2(value);
						break;
					case 20:
						ai.setMail(value);
						break;
					case 21:
						ai.setQq(value);
						break;
					case 22:
						ai.setAddress(value);
						break;
					case 23:
						ai.setZip(value);
						break;
					case 24:
						ai.setNote(value);
						break;
					case 25:
						ai.setAssoc1(value);
						break;
					case 26:
						ai.setAssoc1job(value);
						break;
					case 27:
						ai.setAssoc2(value);
						break;
					case 28:
						ai.setAssoc2job(value);
						break;
				}
			}
			// �ڴ˱�д�Ըö�����д���
			list.add(ai);
		}
		wb.close();
		adminDAO.importAlumniInfos(list);
		return "redirect:/Admin/ExcelToMysql";
	}

	@RequestMapping("/myProfile")
	public String myProfile(HttpSession session, Model m) {
		String roletype = session.getAttribute("roletype").toString();
		if (roletype == null || !roletype.toString().equals("admin")) return "redirect:/Public/login";
		AdminUser au = adminDAO.getAllInfo(session.getAttribute("uid")
				.toString());
		m.addAttribute("role", au.getRole());
		m.addAttribute("auth", au.getAuth());
		m.addAttribute("name", au.getName());
		return "/Admin/myProfile";
	}
}
