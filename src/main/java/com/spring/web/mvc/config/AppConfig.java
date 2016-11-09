package com.spring.web.mvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.servlet.support.csrf.CsrfRequestDataValueProcessor;

@Configuration
public class AppConfig {

	/**
	 * 配置过滤器
	 * 与以下有同样的效果
	 * 在configure中
	 * .csrf().csrfTokenRepository(new HttpSessionCsrfTokenRepository());
	 * @return
	 */
	@Bean
	public CsrfFilter crsfFilter(){
		/**
		 * 将token放到session中去获取
		 */
		return new CsrfFilter(new HttpSessionCsrfTokenRepository());
	}
	/**
	 * 如果用的是spring mvc 的form标签，则配置此项时自动将crsf的token放入到一个hidden的input中，
	 * 而不需要开发人员显式的写入form 
	 * @return
	 */
	@Bean
	public CsrfRequestDataValueProcessor csrfRequestDataValueProcessor(){
		return new CsrfRequestDataValueProcessor();
	}
}
