package cn.xq.xqspringboot.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author Created by lenovo
 * @date 2022/5/23 17:43
 */
@ConfigurationProperties(prefix = "userinfo")
@Data
@Component
@ToString
public class UserInfo {
	private String name;
	private Integer age;
	private Boolean active;
	private Map<String,Object> map;
	/**
	 * jsonFormat 局部配置 会覆盖掉 application.properties 文件下的格式
	 */
//	@JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
	private Date createdDate;
	private List<String> hobbies;
}

