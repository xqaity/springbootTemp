package cn.xq.xqspringboot;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import config.DataSourceConfig;
import factory.YmlConfigFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;



@PropertySource(value = {"classpath:application.yml"},factory = YmlConfigFactory.class)
@SpringBootApplication(scanBasePackages = {"cn.xq","globalexception","utils","factory","enumtype","dynamicdatasource","component","bean","base","config","component","annotation"},exclude = {DruidDataSourceAutoConfigure.class})
@MapperScan(value = {"cn.xq.xqspringboot.mapper","cn.xq.xqspringboot.user.mapper"})
@Import({DataSourceConfig.class})
//@ComponentScan(basePackages = "base")
public class XqSpringbootApplication {
	public static void main(String[] args) {
		SpringApplication.run(XqSpringbootApplication.class, args);
	}

}
