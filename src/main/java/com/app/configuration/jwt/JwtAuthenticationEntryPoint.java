package com.app.configuration.jwt;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class JwtAuthenticationEntryPoint  implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException e) throws IOException, ServletException {
		// TODO Auto-generated method stub
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        System.out.println("entry point");
        String message;
        if(e.getCause() != null) {
            message = e.getCause().getMessage();
        } else {
            message = e.getMessage();
        }
        byte[] body = new ObjectMapper()
                .writeValueAsBytes(Collections.singletonMap("error", message));
        response.getOutputStream().write(body);
        
		
	}

}
