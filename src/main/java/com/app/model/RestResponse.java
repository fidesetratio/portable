package com.app.model;

public class RestResponse {
	
		private String message;
		private int errors;
		private Object error;
		
		public RestResponse(String message, int errors, Object error) {
			this.message = message;
			this.error = error;
			this.errors = errors;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public int getErrors() {
			return errors;
		}

		public void setErrors(int errors) {
			this.errors = errors;
		}

		public Object getError() {
			return error;
		}

		public void setError(Object error) {
			this.error = error;
		}

}
