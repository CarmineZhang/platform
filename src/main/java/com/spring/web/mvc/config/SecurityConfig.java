package com.spring.web.mvc.config;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	

//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		// TODO Auto-generated method stub
//		auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
//	}
	
	/**
	 * csrf��Ĭ�����õģ����Բ���.csrf(),��������csrf��һЩ������
	 * ����defaultSuccessUrl����
	 * 1.��������������ĵ�ַ����Ҫ��¼����ܷ��ʵģ���֤�ɹ���ֱ��redirect�������ַ��
	 * 2.�����ַ/login����֤�ɹ����¼defaultSuccessUrl���õĵ�ַ��
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
		.antMatchers("/logoutSuccess").permitAll()		//˳���ܳ�����������֤����֮ǰ���ò���Ҫ��֤��url
		.anyRequest().authenticated()
		.and()
		.formLogin()
		.loginPage("/login").permitAll() //permitAll() ��Ȩ�����û����Է������·��
		.loginProcessingUrl("/loginSubmit")
		.defaultSuccessUrl("/loginSuccess")
		.and().userDetailsService(new UserDetailsService() {
			
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				// TODO Auto-generated method stub
				System.out.println(username);
				ArrayList<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
				return new User("user", "password",authorities);
			}
		})
		.csrf()
		.and().logout()
		.logoutUrl("/logout")
		.logoutSuccessHandler(new LogoutSuccessHandler() {
			//��ִ��
			public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
					throws IOException, ServletException {
				// TODO Auto-generated method stub
				System.out.println("logoutSuccessHandler");
				response.sendRedirect(request.getContextPath()+"/logoutSuccess");
			}
		}).addLogoutHandler(new LogoutHandler() {
			//��ִ��
			public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
				// TODO Auto-generated method stub
				System.out.println("LogoutHandler");
			}
		});
	}
}
