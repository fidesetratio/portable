package com.app.model;

import java.util.Date;

public class DatabaseDatasource {
		
		private Integer datasource_id;
		private String datasource_name;
		private String datasource_description;
		private Date create_date;
		public Integer getDatasource_id() {
			return datasource_id;
		}
		public void setDatasource_id(Integer datasource_id) {
			this.datasource_id = datasource_id;
		}
		public String getDatasource_name() {
			return datasource_name;
		}
		public void setDatasource_name(String datasource_name) {
			this.datasource_name = datasource_name;
		}
		public String getDatasource_description() {
			return datasource_description;
		}
		public void setDatasource_description(String datasource_description) {
			this.datasource_description = datasource_description;
		}
		public Date getCreate_date() {
			return create_date;
		}
		public void setCreate_date(Date create_date) {
			this.create_date = create_date;
		}
		
		
		

}
