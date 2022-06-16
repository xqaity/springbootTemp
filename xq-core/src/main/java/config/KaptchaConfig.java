//package config;/**
// * @author Created by lenovo
// * @date 2022/5/31 13:35
// */
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.Properties;
//
///**
// * <h3>xq-mode</h3>
// * <p>生成验证码</p>
// *
// * @author : xq
// * @date : 2022-05-31 13:35
// **/
//@Configuration
//public class KaptchaConfig {
//	@Bean
//	public DefaultKaptcha producer() {
//		Properties properties = new Properties();
//		properties.put("kaptcha.border", "no");
//		properties.put("kaptcha.textproducer.font.color", "black");
//		properties.put("kaptcha.textproducer.char.space", "4");
//		properties.put("kaptcha.image.height", "40");
//		properties.put("kaptcha.image.width", "120");
//		properties.put("kaptcha.textproducer.font.size", "30");
//		Config config = new Config(properties);
//		DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
//		defaultKaptcha.setConfig(config);
//		return defaultKaptcha;
//	}
//}