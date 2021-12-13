package com.app.model;

public class SQLColumn {
	
		private String name;
		private Boolean autoincrement;
		private String type;
		private Integer typeCode;
		private String tableName;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Boolean getAutoincrement() {
			return autoincrement;
		}
		public void setAutoincrement(Boolean autoincrement) {
			this.autoincrement = autoincrement;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public Integer getTypeCode() {
			return typeCode;
		}
		public void setTypeCode(Integer typeCode) {
			this.typeCode = typeCode;
		}
		public String getTableName() {
			return tableName;
		}
		public void setTableName(String tableName) {
			this.tableName = tableName;
		}

}
