package config;/**
 * @author Created by lenovo
 * @date 2022/6/9 18:00
 */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * <h3>xq-mode</h3>
 * <p>使用@Async这个注解之前要先配置线程池</p>
 *1、重写SchedulingConfigurer#configureTasks()
 * 直接实现SchedulingConfigurer这个接口，设置taskScheduler，代码如下：
 *
 * @Configuration
 * public class ScheduleConfig implements SchedulingConfigurer {
 *     @Override
 *     public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
 *         //设定一个长度10的定时任务线程池
 *         taskRegistrar.setScheduler(Executors.newScheduledThreadPool(10));
 *     }
 * }
 * 2、通过配置开启
 * Spring Boot quartz 已经提供了一个配置用来配置线程池的大小，如下；
 *
 * spring.task.scheduling.pool.size=10
 * 只需要在配置文件中添加如上的配置即可生效！
 * @author : xq
 * @date : 2022-06-09 18:00
 **/
@Configuration
public class AsyncConfig {
	@Bean
	public ThreadPoolTaskExecutor taskExecutor() {
		ThreadPoolTaskExecutor poolTaskExecutor = new ThreadPoolTaskExecutor();
		poolTaskExecutor.setCorePoolSize(4);
		poolTaskExecutor.setMaxPoolSize(6);
		// 设置线程活跃时间（秒）
		poolTaskExecutor.setKeepAliveSeconds(120);
		// 设置队列容量
		poolTaskExecutor.setQueueCapacity(40);
		poolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		// 等待所有任务结束后再关闭线程池
		poolTaskExecutor.setWaitForTasksToCompleteOnShutdown(true);
		return poolTaskExecutor;
	}
}
