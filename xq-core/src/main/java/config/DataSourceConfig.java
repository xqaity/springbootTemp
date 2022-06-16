package config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import dynamicdatasource.DataSourceContextHolder;
import dynamicdatasource.DataSourceRouter;
import enumtype.DataSourceEnum;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Created by lenovo
 * @date 2022/5/25 16:43
 */
@Configuration
@MapperScan(basePackages = {"cn.xq.xqspringboot.mapper","cn.xq.xqspringboot.user.mapper"},sqlSessionFactoryRef = "sessionFactory",sqlSessionTemplateRef = "sqlTemplate")
public class DataSourceConfig {

	/**
	 * 主库
	 */
	@Bean
	@ConfigurationProperties(prefix = "spring.datasource.master")
	public DataSource master() {
		return DruidDataSourceBuilder.create().build();
	}

	/**
	 * 从库
	 */
	@Bean
	@ConfigurationProperties(prefix = "spring.datasource.slave")
	public DataSource slaver() {
		return DruidDataSourceBuilder.create().build();
	}


	/**
	 * 实例化数据源路由
	 */
	@Bean
	@Primary
	public DataSourceRouter dynamicDB(@Qualifier("master") DataSource masterDataSource,
	                                  @Qualifier("slaver") DataSource slaveDataSource) {
		DataSourceRouter dynamicDataSource = new DataSourceRouter();
//		DataSourceContextHolder.set(DataSourceEnum.SLAVE.getDataSourceName());
		Map<Object, Object> targetDataSources = new HashMap<>();
		targetDataSources.put(DataSourceEnum.MASTER.getDataSourceName(), masterDataSource);
		if (slaveDataSource != null) {
			targetDataSources.put(DataSourceEnum.SLAVE.getDataSourceName(), slaveDataSource);
		}
		dynamicDataSource.setTargetDataSources(targetDataSources);
		dynamicDataSource.setDefaultTargetDataSource(masterDataSource);
		dynamicDataSource.afterPropertiesSet();
		return dynamicDataSource;
	}



	/**
	 * 配置sessionFactory
	 *
	 * @param dynamicDataSource
	 * @return
	 * @throws Exception
	 */
	@Bean
	public SqlSessionFactory sessionFactory(@Qualifier("dynamicDB") DataSource dynamicDataSource) throws Exception {
		MybatisSqlSessionFactoryBean  bean = new MybatisSqlSessionFactoryBean();
		bean.setMapperLocations(
				new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/*Mapper.xml"));
		bean.setDataSource(dynamicDataSource);
		return bean.getObject();
	}

//	@Bean
//	@Primary
//	public MybatisSqlSessionFactoryBean sqlSessionFactoryBean(@Qualifier("dynamicDB") DataSource dataSource
//	) {
//		MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
//		sqlSessionFactoryBean.setDataSource(dataSource);
//		return sqlSessionFactoryBean;
//	}


	/**
	 * 创建sqlTemplate
	 *
	 * @param sqlSessionFactory
	 * @return
	 */
	@Bean
	public SqlSessionTemplate sqlTemplate(@Qualifier("sessionFactory") SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}


	/**
	 * 事务配置
	 *
	 * @param dynamicDataSource
	 * @return
	 */
	@Bean(name = "dataSourceTx")
	public DataSourceTransactionManager dataSourceTransactionManager(@Qualifier("dynamicDB") DataSource dynamicDataSource) {
		DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
		dataSourceTransactionManager.setDataSource(dynamicDataSource);
		return dataSourceTransactionManager;
	}
}