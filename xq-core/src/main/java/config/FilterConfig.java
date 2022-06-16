package config;/**
 * @author Created by lenovo
 * @date 2022/5/27 11:07
 */

import component.filter.CrosFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

import javax.servlet.Filter;

/**
 * <h3>xq-mode</h3>
 * <p>FilterRegistrationBean 注入自己实现的各种过滤</p>
 *
 * @author : xq
 * @date : 2022-05-27 11:07
 **/
@Configuration
@AutoConfigureAfter(Filter.class)
public class FilterConfig {
	@Autowired
	@Qualifier(value = "CrosFilter")
	private CrosFilter crosFilter;
//	/**
//	 * 注入crosFilter
//	 * @return
//	 */
	@Bean
	public FilterRegistrationBean crosFilter() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(crosFilter);
		registration.addUrlPatterns("/*");
		registration.setName("crosFilter");
		//设置优先级别
		registration.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return registration;
	}
}
