package com.app.rests;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.model.UploadForm;

@RestController
public class UploadRestController {
	
		@Autowired
		private Environment env;
		
		
		
		@PostMapping("/rest/uploadfile")
	    public ResponseEntity<?> multiUploadFileModel(@ModelAttribute UploadForm form) {
	 		
	 		

	        System.out.println("Description:" + form.getDescription());

	        String result = null;
	        try {

	            result = this.saveUploadedFiles(form.getFiles());

	        }
	        // Here Catch IOException only.
	        // Other Exceptions catch by RestGlobalExceptionHandler class.
	        catch (IOException e) {
	            e.printStackTrace();
	            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
	        }

	        return new ResponseEntity<String>("Uploaded to: <br/>" + result, HttpStatus.OK);

	    }
	  
	// Save Files
	    private String saveUploadedFiles(MultipartFile[] files) throws IOException {
	        // Make sure directory exists!
	    	String UPLOAD_DIR = env.getProperty("spring.upload");
	    	
	    	File uploadDir = new File(UPLOAD_DIR);
	        uploadDir.mkdirs();
	        StringBuilder sb = new StringBuilder();
	        for (MultipartFile file : files) {

	            if (file.isEmpty()) {
	                continue;
	            }
	            String uploadFilePath = UPLOAD_DIR + File.separator + file.getOriginalFilename();
	            System.out.println(uploadFilePath);

	            byte[] bytes = file.getBytes();
	            Path path = Paths.get(uploadFilePath);
	            Files.write(path, bytes);

	            sb.append(uploadFilePath).append("<br/>");
	        }
	        return sb.toString();
	    }
}
