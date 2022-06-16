package utils;/**
 * @author Created by lenovo
 * @date 2022/5/26 22:56
 */

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * <h3>xq-mode</h3>
 * <p>ApplicationContextUtils 上下文环境</p>
 *AOP使用的是动态代理的机制，它会给类生成一个代理类，事务的相关操作都在代理类上完成。
 * 内部方式使用this调用方式时，使用的是实例调用，并没有通过代理类调用方法，所以会导致事务失效。
 * getApplicationContext.getBean的方式代理调用使其内部调用生效
 * @author : xq
 * @date : 2022-05-26 22:56
 **/
public class ApplicationContextUtils implements ApplicationContextAware {
	private static ApplicationContext application;

	public static ApplicationContext getApplicationContext() {
		return application;
	}
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		ApplicationContextUtils.application = applicationContext;
	}
}
