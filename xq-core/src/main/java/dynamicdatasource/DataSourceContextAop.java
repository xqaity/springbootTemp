package dynamicdatasource;

import annotation.DataSourceSwitcher;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
//
///**
// * @author Created by lenovo
// * @date 2022/5/25 22:26
// */
@Slf4j
@Aspect
@Order(value = Ordered.HIGHEST_PRECEDENCE)
@Component
public class DataSourceContextAop {

	@Around("@annotation(annotation.DataSourceSwitcher)")
	public Object setDynamicDataSource(ProceedingJoinPoint pjp) throws Throwable {
		boolean clear = false;
		try {
			Method method = this.getMethod(pjp);
			DataSourceSwitcher dataSourceSwitcher = method.getAnnotation(DataSourceSwitcher.class);
			clear = dataSourceSwitcher.clear();
			DataSourceContextHolder.set(dataSourceSwitcher.value().getDataSourceName());
			log.info("数据源切换至：{}", dataSourceSwitcher.value().getDataSourceName());
			return pjp.proceed();
		} finally {
			if (clear) {
				DataSourceContextHolder.clear();
			}

		}
	}


	private Method getMethod(JoinPoint pjp) {
		MethodSignature signature = (MethodSignature) pjp.getSignature();
		return signature.getMethod();
	}

}