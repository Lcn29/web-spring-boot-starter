package io.github.lcn29.web.starter.interceptor;

import io.github.lcn29.web.starter.constant.RequestHeader;
import io.github.lcn29.web.starter.util.LogTraceIdUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * TraceId 拦截器
 *
 * @author lcn29
 * @date 2024-05-05 17:38:29
 */
public class TraceIdInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 从请求头中尝试获取 traceId, 获取不到自动生成
        String trackId = request.getHeader(RequestHeader.X_TRACE_ID);
        LogTraceIdUtils.setTraceId(trackId);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        LogTraceIdUtils.removeTraceId();
    }
}
