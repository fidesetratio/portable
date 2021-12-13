package com.app.rests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.RestResponse;
import com.app.model.UploadForm;
import com.app.model.User;
import com.app.services.DashboardServices;

@RestController
public class UserManagementRest {
	
	@Autowired
	private DashboardServices ds;
	
	
	

	@PostMapping("/rest/userdelete")
	@Transactional
	 public RestResponse userdelete(@RequestBody User user) {
		
		RestResponse restResponse = new RestResponse("Username is succesfully deleted", 0, null);
		System.out.println("/rest/userdelete");
		boolean allowed = false;
		if(user != null) {
			if(user.getUserId() == 1) {
				allowed = false;
				restResponse.setMessage("This username is not allowed to delete");
				restResponse.setErrors(1);
			}else {
				allowed = true;
			}
			
		}
		try {
			if(allowed) {
			ds.deleteUserGroup(user);
			ds.deleteUser(user);
			}
		}catch(Exception e) {
			e.printStackTrace();
			restResponse.setMessage("There is a problem, please contact administrator");
			restResponse.setErrors(1);
		}
		return restResponse;
	}
 	
}
