package cn.xq.xqspringboot.config;
import bean.LinuxCondition;
import bean.WindowsCondition;
import cn.xq.xqspringboot.vo.Custom;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @author Created by lenovo
 * @date 2022/5/25 9:49
 */
@Configuration
public class CustomConfig {

	@Bean("windows")
	@Conditional(value = {WindowsCondition.class})
	public Custom win() {
		Custom custom = new Custom();
		custom.setItem("winItem");
		return custom;
	}

	@Bean("linux")
	@Conditional(value = {LinuxCondition.class})
	public Custom linux() {
		Custom custom = new Custom();
		custom.setItem("linuxItem");
		return custom;
	}
}
