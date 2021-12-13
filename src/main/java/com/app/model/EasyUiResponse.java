package com.app.model;

import java.util.List;

public class EasyUiResponse<T> {
	
	  private Boolean success;

	  private Integer total;

	  private List<T> rows;
	  
	  public EasyUiResponse(Boolean success, Integer total, List<T> rows) {
		  this.success = success;
		  this.total = total;
		  this.rows = rows;
	  }

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	  

}
