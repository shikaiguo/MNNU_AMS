package cn.edu.mnnu.ams.model;

public class Messages {
	private int messid;
	private int alumniid;
	private String date;
	private String content;
	private String dept;
	private String major;
	private String cls;
	private int toid;
	public int getMessid() {
		return messid;
	}
	public void setMessid(int messid) {
		this.messid = messid;
	}
	public int getAlumniid() {
		return alumniid;
	}
	public void setAlumniid(int alumniid) {
		this.alumniid = alumniid;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getCls() {
		return cls;
	}
	public void setCls(String cls) {
		this.cls = cls;
	}
	public int getToid() {
		return toid;
	}
	public void setToid(int toid) {
		this.toid = toid;
	}
}
