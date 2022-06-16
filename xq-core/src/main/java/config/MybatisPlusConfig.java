package config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusPropertiesCustomizer;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.handlers.MybatisEnumTypeHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Exrickx
 */
@Configuration
@MapperScan(value = {"cn.xq.xqspringboot.mapper","cn.xq.xqspringboot.user.mapper"})
public class MybatisPlusConfig {

    /**
     * 新的分页插件
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {

        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }
//    @Bean
//    public MybatisPlusPropertiesCustomizer mybatisPlusPropertiesCustomizer() {
//        // 序列化枚举值为数据库存储值
////        FastJsonConfig config = new FastJsonConfig();
////        config.setSerializerFeatures(SerializerFeature.WriteEnumUsingToString);
//
//        return properties -> {
//            GlobalConfig globalConfig = properties.getGlobalConfig();
//            globalConfig.setBanner(false);
//            MybatisConfiguration configuration = new MybatisConfiguration();
//            configuration.setDefaultEnumTypeHandler(MybatisEnumTypeHandler.class);
//            properties.setConfiguration(configuration);
//        };
//    }

}
