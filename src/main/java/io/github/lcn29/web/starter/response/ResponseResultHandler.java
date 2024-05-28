package io.github.lcn29.web.starter.response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 响应结果处理器
 *
 * @author lcn29
 * @date 2024-05-05 17:23:15
 */
@ControllerAdvice
public class ResponseResultHandler implements ResponseBodyAdvice<Object> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResponseResultHandler.class);

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(@Nullable Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {

        // springboot 内部会添加一个 StringHttpMessageConverter 的转换器
        // 如果返回结果为 string, StringHttpMessageConverter 会对 string 进行处理
        // 会导致 ResponseResultAdvice 异常, 移除这个转换器
        // 使用时, 需要将 StringHttpMessageConverter 从 HttpMessageConverter 中移除

        // Content-Type 不是 JSON 格式 或者返回结果已经是 Response, 直接返回结果
        if (!MediaType.APPLICATION_JSON.equals(selectedContentType) || body instanceof Response<?>) {
            return body;
        }
        return ResponseBuilder.success(body);
    }

}
