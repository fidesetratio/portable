package com.app.model;

public class EasyUIApplication {
	
		private Integer noId;
		
		public EasyUIApplication(Integer noId, String applicationName, String description) {
			super();
			this.noId = noId;
			this.applicationName = applicationName;
			this.description = description;
		}

		private String applicationName;
		
		private String description;

		public Integer getNoId() {
			return noId;
		}

		public void setNoId(Integer noId) {
			this.noId = noId;
		}

		public String getApplicationName() {
			return applicationName;
		}

		public void setApplicationName(String applicationName) {
			this.applicationName = applicationName;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}
		
		

}
