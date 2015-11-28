package cn.edu.mnnu.ams.model;

import cn.edu.mnnu.ams.entity.ExamineVerify;

public class ExamineInfo {
	private int id;
	private int userid;
	private String filed;
	private String oldcontent;
	private String content;
	private String note;
	public ExamineInfo(ExamineVerify ev){
		this.id=ev.getId();
		this.userid=ev.getUserid();
		this.filed=ev.getFiled();
		this.oldcontent="";
		this.content=ev.getContent();
		this.note=ev.getNote();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getFiled() {
		return filed;
	}
	public void setFiled(String filed) {
		this.filed = filed;
	}
	public String getOldcontent() {
		return oldcontent;
	}
	public void setOldcontent(String oldcontent) {
		this.oldcontent = oldcontent;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
}
