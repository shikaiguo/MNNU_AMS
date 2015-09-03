package cn.edu.mnnu.ams.model;

import java.util.Collections;
import java.util.List;

public class JqGridData<T>{
	
	private List<T> gridModel =Collections.emptyList();
	private Integer rows;
	private Integer records;
	private Integer page;
	private Integer total;
	private String sidx;
	private String search;
	
	public List<T> getGridModel() {
		return gridModel;
	}
	public void setGridModel(List<T> gridModel) {
		this.gridModel = gridModel;
	}
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public Integer getRecords() {
		return records;
	}
	public void setRecords(Integer records) {
		this.records = records;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public String getSidx() {
		return sidx;
	}
	public void setSidx(String sidx) {
		this.sidx = sidx;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
}