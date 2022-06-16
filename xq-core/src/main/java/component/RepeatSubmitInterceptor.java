package component;

import annotation.RepeatSubmit;
import globalexception.RepeatSubmitException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Objects;

/**
 * 用ioc的方式实现 不用aop在每个restcontroller上面去写注解统一防止重复提交拦截
 * @author Created by lenovo
 * @date 2022/5/24 11:32
 */
@Component
//@Conditional()
public class RepeatSubmitInterceptor implements HandlerInterceptor {

	/**
	 * Redis的API
	 */
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	@Resource(name = "lua1")
	private RedisScript<Long> limitScript;

	@Autowired(required=true)
	private RedisTemplate<Object, Object> redisTemplate;

	/**
	 * preHandler方法，在controller方法之前执行
	 *
	 * 判断条件仅仅是用了uri，实际开发中根据实际情况组合一个唯一识别的条件。
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (handler instanceof HandlerMethod){
			//只拦截标注了@RepeatSubmit该注解

			HandlerMethod method=(HandlerMethod)handler;
			//标注在方法上的@RepeatSubmit
			RepeatSubmit repeatSubmitByMethod = AnnotationUtils.findAnnotation(method.getMethod(), RepeatSubmit.class);
			//标注在controler类上的@RepeatSubmit
			RepeatSubmit repeatSubmitByCls = AnnotationUtils.findAnnotation(method.getMethod().getDeclaringClass(), RepeatSubmit.class);
			//没有限制重复提交，直接跳过
			if (Objects.isNull(repeatSubmitByMethod)&&Objects.isNull(repeatSubmitByCls))
			{return true;}
			// todo: 组合判断条件，这里仅仅是演示，实际项目中根据架构组合条件
			//请求的URI
			String uri = request.getRequestURI();
			//带上限流次数执行lua
			Long number = redisTemplate.execute(limitScript,
					Collections.singletonList(uri),
					Objects.nonNull(repeatSubmitByMethod)?repeatSubmitByMethod.count():repeatSubmitByCls.count(),
					Objects.nonNull(repeatSubmitByMethod)?repeatSubmitByMethod.seconds():repeatSubmitByCls.seconds());
			if (number==null || number.intValue() > (Objects.nonNull(repeatSubmitByMethod)?repeatSubmitByMethod.count():repeatSubmitByCls.count())) {
				throw new RepeatSubmitException();
			}
			//不带限流次数根据时间间隔限流的话 只用设置seconds 不管count
//			Boolean ifAbsent = stringRedisTemplate.opsForValue().setIfAbsent(uri, "", Objects.nonNull(repeatSubmitByMethod)?repeatSubmitByMethod.seconds():repeatSubmitByCls.seconds(), TimeUnit.SECONDS);
//			//如果存在，表示已经请求过了，直接抛出异常，由全局异常进行处理返回指定信息
//			if (ifAbsent!=null&&!ifAbsent)
//			{throw new RepeatSubmitException();}
		}
		return true;
	}



}
