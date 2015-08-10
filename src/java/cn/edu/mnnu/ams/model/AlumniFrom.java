package cn.edu.mnnu.ams.model;

public class AlumniFrom {
	private int id;
	private int Provinceid;
	private int cityid;
	private int districtid;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProvinceid() {
		return Provinceid;
	}

	public void setProvinceid(int provinceid) {
		if(provinceid==0)
			return;
		Provinceid = provinceid;
	}

	public int getCityid() {
		return cityid;
	}

	public void setCityid(int cityid) {
		if(cityid==0)
			return;
		this.cityid = cityid;
	}

	public int getDistrictid() {
		return districtid;
	}

	public void setDistrictid(int districtid) {
		if(districtid==0)
			return;
		this.districtid = districtid;
	}

}
