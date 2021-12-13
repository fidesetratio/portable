package com.app.model;

import java.util.Date;

public class DynamicQuery {
		private Integer queryId;
		private String queryString;
		private int flag_active;
		private String description;
		private Date created_date;
		private String datasource_name;
		public Integer getQueryId() {
			return queryId;
		}
		public void setQueryId(Integer queryId) {
			this.queryId = queryId;
		}
		public String getQueryString() {
			return queryString;
		}
		public void setQueryString(String queryString) {
			this.queryString = queryString;
		}
		public int getFlag_active() {
			return flag_active;
		}
		public void setFlag_active(int flag_active) {
			this.flag_active = flag_active;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
	
		
		public String getDatasource_name() {
			return datasource_name;
		}
		public void setDatasource_name(String datasource_name) {
			this.datasource_name = datasource_name;
		}
		public Date getCreated_date() {
			return created_date;
		}
		public void setCreated_date(Date created_date) {
			this.created_date = created_date;
		}

}
