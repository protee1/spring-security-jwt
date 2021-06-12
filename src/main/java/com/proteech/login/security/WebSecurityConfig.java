package com.proteech.login.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.proteech.login.security.jwt.JwtAuthEntryPoint;
import com.proteech.login.security.jwt.JwtAuthTokenFilter;
import com.proteech.login.security.services.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
		prePostEnabled = true
		)
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter{
	@Autowired
	UserDetailsServiceImpl userDetailsService;
	
	@Autowired
	private JwtAuthEntryPoint anauthorizedhandler;
	
	@Bean
	public JwtAuthTokenFilter authenticationJwtTokenFilter() {
		return new JwtAuthTokenFilter();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
 auth
 .userDetailsService(userDetailsService)
 .passwordEncoder(passwordEncoder());
	}
	
	@Bean
@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManagerBean();
	}

@Bean
	private PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

@Override
protected void configure(HttpSecurity http) throws Exception {
	http
	.cors()
	.and()
	.csrf().disable()
	.authorizeRequests()
	.antMatchers("/api/auth/**").permitAll()
	.anyRequest().authenticated()
	.and()
	.exceptionHandling().authenticationEntryPoint(anauthorizedhandler)
	.and()
	.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	
	http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
}

	
	

	
	
	

}
