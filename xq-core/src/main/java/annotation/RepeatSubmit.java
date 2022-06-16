package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 防止重复提交
 * @author Created by lenovo
 * @date 2022/5/24 11:31
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RepeatSubmit {
	/**
	 * 默认失效时间5秒
	 */
	long seconds() default 5;
	/**
	 * 触发次数
	 */
	long count() default 3;
}
