package annotation;

/**
 * @author Created by lenovo
 * @date 2022/5/25 22:24
 */

import enumtype.DataSourceEnum;

import java.lang.annotation.*;

/**
 * 切换数据源的注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface DataSourceSwitcher {
	/**
	 * 默认数据源
	 * @return
	 */
	DataSourceEnum value() default DataSourceEnum.MASTER;
	/**
	 * 清除
	 * @return
	 */
	boolean clear() default true;

}