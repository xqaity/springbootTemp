package config;

/**
 * @author Created by lenovo
 * @date 2022/5/24 10:49
 */

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import enumtype.DatePattern;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.time.ZoneId;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 自定义jackson序列化与反序列规则，增加相关格式（全局配置）
 */
@Configuration
public class JacksonConfig {
	@Bean
	@Primary
	@ConditionalOnMissingBean
	public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder) {
		builder.locale(Locale.CHINA);
		builder.timeZone(TimeZone.getTimeZone(ZoneId.systemDefault()));
		builder.simpleDateFormat(DatePattern.NORM_DATETIME_PATTERN);
//		builder.modules(new CustomTimeModule());

		ObjectMapper objectMapper = builder.createXmlMapper(false).build();

		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);

		//遇到未知属性的时候抛出异常，//为true 会抛出异常
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		// 允许出现特殊字符和转义符
		objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
		// 允许出现单引号
		objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);

//		objectMapper.registerModule(new CustomTimeModule());

		return objectMapper;
	}

}
