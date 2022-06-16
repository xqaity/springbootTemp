package annotation;/**
 * @author Created by lenovo
 * @date 2022/5/27 15:43
 */

import component.vo.RedisLockTypeEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <h3>xq-mode</h3>
 * <p>设定被拦截的注解名字</p>
 *
 * @author : xq
 * @date : 2022-05-27 15:43
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface RedisLockAnnotation {
	/**
	 * 特定参数识别，默认取第 0 个下标
	 */
	int lockFiled() default 0;
	/**
	 * 超时重试次数
	 */
	int tryCount() default 3;
	/**
	 * 自定义加锁类型
	 */
	RedisLockTypeEnum typeEnum();
	/**
	 * 释放时间，秒 s 单位
	 */
	long lockTime() default 10;
}