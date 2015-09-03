package cn.edu.mnnu.ams.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="alumni_infos")
public class AlumniInfos {
	@Id
	private int id;
	private String sno;
	private String dept;
	private String major;
	@Column(name="class")
	private String cls;
	private String sname;
	private String sex;
	@Column(name="province_from")
	private String provincefrom;
	@Column(name="city_from")
	private String cityfrom;
	@Column(name="district_from")
	private String districtfrom;
	private String degree;
	@Column(name="gra_time")
	private String gratime;
	@Column(name="province_work")
	private String provincework;
	@Column(name="city_work")
	private String citywork;
	@Column(name="district_work")
	private String districtwork;
	@Column(name="work_unit")
	private String workunit;
	private String duty;
	private String job;
	private String profession;
	private String phone1;
	private String phone2;
	private String mail;
	private String qq;
	private String address;
	private String zip;
	private String note;
	private String assoc1;
	@Column(name="assoc1_job")
	private String assoc1job;
	private String assoc2;
	@Column(name="assoc2_job")
	private String assoc2job;
	@Column(name="update_time")
	private String updatetime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSno() {
		return sno;
	}
	public void setSno(String sno) {
		this.sno = sno;
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
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getProvincefrom() {
		return provincefrom;
	}
	public void setProvincefrom(String provincefrom) {
		this.provincefrom = provincefrom;
	}
	public String getCityfrom() {
		return cityfrom;
	}
	public void setCityfrom(String cityfrom) {
		this.cityfrom = cityfrom;
	}
	public String getDistrictfrom() {
		return districtfrom;
	}
	public void setDistrictfrom(String districtfrom) {
		this.districtfrom = districtfrom;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	public String getGratime() {
		return gratime;
	}
	public void setGratime(String gratime) {
		this.gratime = gratime;
	}
	public String getProvincework() {
		return provincework;
	}
	public void setProvincework(String provincework) {
		this.provincework = provincework;
	}
	public String getCitywork() {
		return citywork;
	}
	public void setCitywork(String citywork) {
		this.citywork = citywork;
	}
	public String getDistrictwork() {
		return districtwork;
	}
	public void setDistrictwork(String districtwork) {
		this.districtwork = districtwork;
	}
	public String getWorkunit() {
		return workunit;
	}
	public void setWorkunit(String workunit) {
		this.workunit = workunit;
	}
	public String getDuty() {
		return duty;
	}
	public void setDuty(String duty) {
		this.duty = duty;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public String getPhone1() {
		return phone1;
	}
	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}
	public String getPhone2() {
		return phone2;
	}
	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getAssoc1() {
		return assoc1;
	}
	public void setAssoc1(String assoc1) {
		this.assoc1 = assoc1;
	}
	public String getAssoc1job() {
		return assoc1job;
	}
	public void setAssoc1job(String assoc1job) {
		this.assoc1job = assoc1job;
	}
	public String getAssoc2() {
		return assoc2;
	}
	public void setAssoc2(String assoc2) {
		this.assoc2 = assoc2;
	}
	public String getAssoc2job() {
		return assoc2job;
	}
	public void setAssoc2job(String assoc2job) {
		this.assoc2job = assoc2job;
	}
	public String getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

}
