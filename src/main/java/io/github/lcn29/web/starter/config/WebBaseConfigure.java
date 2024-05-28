package io.github.lcn29.web.starter.config;

import io.github.lcn29.web.starter.exception.GlobalExceptionHandler;
import io.github.lcn29.web.starter.interceptor.TraceIdInterceptor;
import io.github.lcn29.web.starter.json.JacksonObjectMapperBuilderCustomizer;
import io.github.lcn29.web.starter.json.convert.String2LocalDateConverter;
import io.github.lcn29.web.starter.json.convert.String2LocalDateTimeConverter;
import io.github.lcn29.web.starter.response.ResponseResultHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * Web 基础配置
 *
 * @author lcn29
 * @date 2024-05-05 17:40:40
 */
public class WebBaseConfigure implements WebMvcConfigurer {

    @Bean
    public GlobalExceptionHandler globalExceptionHandler() {
        // 初始全局异常捕获处理器
        return new GlobalExceptionHandler();
    }

    @Bean
    public ResponseResultHandler responseResultHandler() {
        // 响应结果转 Response 处理器
        return new ResponseResultHandler();
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

        // spring boot 内部会添加一个 StringHttpMessageConverter 的转换器
        // 如果返回结果为 string, StringHttpMessageConverter 会对 string 进行处理
        // 会导致 ResponseResultAdvice 异常, 移除这个转换器
        converters.removeIf(converter -> converter instanceof StringHttpMessageConverter);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 拦截器
        registry.addInterceptor(new TraceIdInterceptor());
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new String2LocalDateTimeConverter());
        registry.addConverter(new String2LocalDateConverter());
    }

    @Bean
    @ConditionalOnMissingBean(Jackson2ObjectMapperBuilderCustomizer.class)
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {

        // 添加请求参数和响应体的数据类型序列化和反序列化器
        // 内部默认添加了 LocalDateTime/LocalDate 的序列化和反序列化 Long/BigInt 的序列化
        // 默认的序列化方式可以被 @JsonDeserialize 和 @JsonSerialize 注解覆盖
        return new JacksonObjectMapperBuilderCustomizer().generateJackson2ObjectMapperBuilderCustomizer();
    }

}
