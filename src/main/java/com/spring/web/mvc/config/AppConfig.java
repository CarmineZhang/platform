package com.spring.web.mvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.servlet.support.csrf.CsrfRequestDataValueProcessor;

@Configuration
public class AppConfig {

	/**
	 * ���ù�����
	 * ��������ͬ����Ч��
	 * ��configure��
	 * .csrf().csrfTokenRepository(new HttpSessionCsrfTokenRepository());
	 * @return
	 */
	@Bean
	public CsrfFilter crsfFilter(){
		/**
		 * ��token�ŵ�session��ȥ��ȡ
		 */
		return new CsrfFilter(new HttpSessionCsrfTokenRepository());
	}
	/**
	 * ����õ���spring mvc ��form��ǩ�������ô���ʱ�Զ���crsf��token���뵽һ��hidden��input�У�
	 * ������Ҫ������Ա��ʽ��д��form 
	 * @return
	 */
	@Bean
	public CsrfRequestDataValueProcessor csrfRequestDataValueProcessor(){
		return new CsrfRequestDataValueProcessor();
	}
}
