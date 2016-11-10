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
	 * csrf是默认启用的，可以不用.csrf(),除非配置csrf的一些东西。
	 * 关于defaultSuccessUrl问题
	 * 1.如果浏览器中输入的地址是需要登录后才能访问的，验证成功后直接redirect到输入地址中
	 * 2.如果地址/login，验证成功后登录defaultSuccessUrl设置的地址。
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
		.antMatchers("/logoutSuccess").permitAll()		//顺序不能出错，必须在验证请求之前设置不需要验证的url
		.anyRequest().authenticated()
		.and()
		.formLogin()
		.loginPage("/login").permitAll() //permitAll() 授权所有用户可以访问这个路径
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
			//后执行
			public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
					throws IOException, ServletException {
				// TODO Auto-generated method stub
				System.out.println("logoutSuccessHandler");
				response.sendRedirect(request.getContextPath()+"/logoutSuccess");
			}
		}).addLogoutHandler(new LogoutHandler() {
			//先执行
			public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
				// TODO Auto-generated method stub
				System.out.println("LogoutHandler");
			}
		});
	}
}
