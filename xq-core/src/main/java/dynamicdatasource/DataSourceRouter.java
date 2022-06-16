package dynamicdatasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;


/**
 * @author Created by lenovo
 * @date 2022/5/25 22:16
 */
public class DataSourceRouter extends AbstractRoutingDataSource {

	/**
	 * 最终的determineCurrentLookupKey返回的是从DataSourceContextHolder中拿到的,因此在动态切换数据源的时候注解
	 * 应该给DataSourceContextHolder设值
	 *
	 * @return
	 */
	@Override
	protected Object determineCurrentLookupKey() {
		return DataSourceContextHolder.get();
	}



	}