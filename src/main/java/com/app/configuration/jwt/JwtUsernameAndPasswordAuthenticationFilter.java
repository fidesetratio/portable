package com.app.configuration.jwt;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;



import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUsernameAndPasswordAuthenticationFilter  extends AbstractAuthenticationProcessingFilter  {

	// We use auth manager to validate the user credentials
		private AuthenticationManager authManager;
		private final ObjectMapper mapper;


	
		public JwtUsernameAndPasswordAuthenticationFilter(AuthenticationManager authManager) {
			super(new AntPathRequestMatcher("/api/login", "POST"));
			System.out.println("patar");
			if(authManager == null) {
				System.out.println("is null");
			}
			setAuthenticationManager(authManager);
			JsonFactory factory = new JsonFactory();
			factory.enable(JsonParser.Feature.ALLOW_SINGLE_QUOTES);

			this.mapper = new ObjectMapper(factory);
			this.mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
		}

		
		
		@Override
		public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
				throws AuthenticationException {
			
			try {
				
				System.out.println("patar timotius");
				// 1. Get credentials from request
				UserCredentials creds = new ObjectMapper().readValue(request.getInputStream(), UserCredentials.class);
				
				System.out.println(creds.getUsername());
				// 2. Create auth object (contains credentials) which will be used by auth manager
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
						creds.getUsername(), creds.getPassword(), Collections.emptyList());
				
				// 3. Authentication manager authenticate the user, and use UserDetialsServiceImpl::loadUserByUsername() method to load the user.
				return getAuthenticationManager().authenticate(authToken);
				
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		
		
		// Upon successful authentication, generate a token.
		// The 'auth' passed to successfulAuthentication() is the current authenticated user.
		@Override
		protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
				Authentication auth) throws IOException, ServletException {
			
			Long now = System.currentTimeMillis();
			/*String token = Jwts.builder()
				.setSubject(auth.getName())	
				// Convert to list of strings. 
				// This is important because it affects the way we get them back in the Gateway.
				.claim("authorities", auth.getAuthorities().stream()
					.map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.setIssuedAt(new Date(now))
				.setExpiration(new Date(now + 100 * 1000))  // in milliseconds
				.signWith(SignatureAlgorithm.HS512, "xixixi".getBytes())
				.compact();*/
			
			String token = "xxxx";
			// Add token to header
			//response.addHeader("Authorization", "Bearer " + token);
			Map<String, String> tokenMap = new HashMap<String, String>();
			tokenMap.put("token", token);
			response.setStatus(HttpStatus.OK.value());
			response.setContentType(MediaType.APPLICATION_JSON_VALUE);
			
			try {
				mapper.writeValue(response.getWriter(), tokenMap);
			} catch (JsonGenerationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			clearAuthenticationAttributes(request);


			
		}
		protected final void clearAuthenticationAttributes(HttpServletRequest request) {
			HttpSession session = request.getSession(false);
			if (session == null) {
				return;
			}
			session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
		}

		 @Override
		    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
			
			 Map<String, String> tokenMap = new HashMap<String, String>();
				tokenMap.put("error", "UnsuccessfulAuthentication");
				response.setStatus(HttpStatus.UNAUTHORIZED.value());
				response.setContentType(MediaType.APPLICATION_JSON_VALUE);
				
				try {
					mapper.writeValue(response.getWriter(), tokenMap);
				} catch (JsonGenerationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JsonMappingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				clearAuthenticationAttributes(request);

		 }

	
	private static class UserCredentials {
	    private String username, password;

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
	    
	}
	
}
