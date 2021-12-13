package com.app.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DynamicResultQuery {
	
		private List<SQLColumn> headers;
		
		private int total;
		private int status;
		private String statusMessage;
		private List<HashMap<String,Object>> body;
		
		
		
		
		public DynamicResultQuery() {
			headers = new ArrayList<SQLColumn>();
			total = 0;
			body = new ArrayList<HashMap<String,Object>>();
			status = 0;
			statusMessage = "Success";
			
		}




	


		public int getTotal() {
			return total;
		}




		public void setTotal(int total) {
			this.total = total;
		}




		public List<HashMap<String, Object>> getBody() {
			return body;
		}




		public void setBody(List<HashMap<String, Object>> body) {
			this.body = body;
		}







		public List<SQLColumn> getHeaders() {
			return headers;
		}







		public void setHeaders(List<SQLColumn> headers) {
			this.headers = headers;
		}







		public int getStatus() {
			return status;
		}







		public void setStatus(int status) {
			this.status = status;
		}







		public String getStatusMessage() {
			return statusMessage;
		}







		public void setStatusMessage(String statusMessage) {
			this.statusMessage = statusMessage;
		}

}
