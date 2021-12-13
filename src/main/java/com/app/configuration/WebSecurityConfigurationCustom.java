package com.app.configuration;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.app.configuration.basic.BasicFilterLoginFilter;
import com.app.configuration.jwt.JwtAuthenticationEntryPoint;
import com.app.configuration.jwt.JwtAuthenticationProvider;
import com.app.configuration.jwt.JwtAuthenticationTokenFilter;
import com.app.configuration.jwt.JwtUsernameAndPasswordAuthenticationFilter;
import com.app.sec.provider.MysqlAuthenticationProvider;
import com.app.sec.provider.customer.CustomerUserDetailsService;
import com.app.sec.provider.handler.CustomAccessDenied;
import com.app.services.DashboardServices;

@EnableWebSecurity
public class WebSecurityConfigurationCustom {

	
	@Configuration
	@Order(1)
	public class JwtWebSecurityConfiguration extends WebSecurityConfigurerAdapter{

	    @Autowired
	    private JwtAuthenticationEntryPoint unauthorizedHandler;
	    
	    @Autowired
	    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
	    
	    private String secret = "xixixxi";
	    

	    @Autowired
	    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) {
	        authenticationManagerBuilder.authenticationProvider(jwtAuthenticationProvider());
	    }
	    
	    
	    @Override
	    public void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth.authenticationProvider(jwtAuthenticationProvider())
	                .inMemoryAuthentication().withUser("user").password("user").roles("USER");
	    }
	    
	    @Override
	    protected void configure(HttpSecurity httpSecurity) throws Exception {
	        httpSecurity.csrf().disable()
	                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	                .and().anonymous().and().exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
	                .and().antMatcher("/api/**").authorizeRequests().anyRequest().authenticated();
	        
	        httpSecurity.addFilterBefore(jwtUsernameAndFilter() , UsernamePasswordAuthenticationFilter.class);
	        httpSecurity.headers().cacheControl();
	    }
	    
	    @Bean
	    public JwtUsernameAndPasswordAuthenticationFilter jwtUsernameAndFilter() throws Exception {
	    	return new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager());
	    }
	    
	    
	    
	    @Bean
	    public JwtAuthenticationProvider jwtAuthenticationProvider() {
	        return new JwtAuthenticationProvider(secret);
	    }
	    	    
	}
	
	

	
	
	

    
	
	@Configuration
	@Order(2)
	public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {
		
		@Autowired
		private DashboardServices dashboardServices;
		
		
		@Autowired
		private DataSource dataSource;
		
		@Autowired
		private CustomerUserDetailsService userDetailsService;
		
		 @Override
		    protected void configure(HttpSecurity http) throws Exception {
		      /*  http.authorizeRequests()
		        	    .anyRequest().authenticated()
		                .and()
		            .formLogin()
		                .loginPage("/login")
		                .defaultSuccessUrl("/dashboard", true)
		                .permitAll()
		                .and()
		                .rememberMe().tokenRepository(persistentTokenRepository);
		                */
			 
			 http.authorizeRequests()
		        .antMatchers("/login", "/register","/jwt/login")
		        .permitAll()
		        .antMatchers("/dashboard").hasRole("USER")
		        .antMatchers("/submitprofile").hasRole("USER")
		        .antMatchers("/easyui").hasRole("USER")
			    
		        .and()
		        .rememberMe().tokenRepository(persistentTokenRepository())
		    //    .userDetailsService(this.userDetailsService())
		        .and()
		        .formLogin()
		        .defaultSuccessUrl("/dashboard")
		        .loginPage("/login")
		        .failureUrl("/login?error=true")
		        .and()
		        .logout().deleteCookies("dummyCookie");
			     
			 http.exceptionHandling().accessDeniedHandler(new CustomAccessDenied());
		    }
		 
		  @Override
		    public void configure(WebSecurity web) throws Exception {   
		        web.ignoring().antMatchers("/css/**","/images/**","/js/**","/imgu/**");
		    }
		    
		 
		 @Autowired
		    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		    	auth.authenticationProvider(new MysqlAuthenticationProvider(dashboardServices));
		    	try {
		    		auth.userDetailsService(userDetailsService);
		    	}catch(Exception e) {
		    		//e.printStackTrace();
		    	}
		 }	 

			@Bean
		    public PersistentTokenRepository persistentTokenRepository() {
		        JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
		        db.setDataSource(dataSource);
		        return db;
		   }

			@Bean
		    public BasicFilterLoginFilter basicLoginFilter() throws Exception {
		    	return new BasicFilterLoginFilter(authenticationManager());
		    }
		    	
			
	}



	
	
	@SuppressWarnings("deprecation")
	@Bean
	public static NoOpPasswordEncoder passwordEncoder() {
	return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
	}


}
