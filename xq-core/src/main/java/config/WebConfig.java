package config;

import component.RepeatSubmitInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebMvcAutoConfigurationAdapter这个子配置类实现了WebMvcConfigurer这个接口，这个正是MVC扩展接口，
 * 这个就很清楚了。自动配置类是在项目启动的时候就加载的，
 * 因此Spring Boot会在项目启动时加载WebMvcAutoConfigurationAdapter这个MVC扩展配置类，
 * 提前完成一些默认的配置（比如内置了默认的视图解析器，资源映射处理器等等），这也就是为什么没有配置什么MVC相关的东西依然能够运行。
 * 其他的配置拦截等继承webmvcconfig就行
 * @EnableWebMvc：全面接管MVC，导致springboot的自动配置类失效
 * 一切都已经揭晓了，@EnableWebMvc导入了一个WebMvcConfigurationSupport类型的配置类，
 * 导致了自动配置类WebMvcAutoConfiguration标注的
 * @ConditionalOnMissingBean(WebMvcConfigurationSupport.class)判断为false了，从而自动配置类失效了。
 *
 * @author Created by lenovo
 * @date 2022/5/24 11:20
 */
//@EnableWebMvc
@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Autowired
	private RepeatSubmitInterceptor repeatSubmitInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//不拦截的uri
		final String[] commonExclude = {"/error", "/files/**"};
//		registry.addInterceptor();
		registry.addInterceptor(repeatSubmitInterceptor).excludePathPatterns(commonExclude);
	}
}
