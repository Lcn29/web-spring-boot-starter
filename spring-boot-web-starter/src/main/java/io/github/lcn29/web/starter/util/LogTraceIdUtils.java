package io.github.lcn29.web.starter.util;

import org.slf4j.MDC;
import org.springframework.util.StringUtils;

/**
 * 日志 traceId 处理器
 *
 * @author lcn29
 * @date 2024-05-05 17:33:05
 */
public class LogTraceIdUtils {

    /**
     * 日志文件中, traceId 的标识
     */
    private final static String LOG_TRACK_ID_MARK = "traceId";

    /**
     * 向日志上下文添加 TraceId
     *
     * @param traceId traceId
     */
    public static void setTraceId(String traceId) {
        String traceIdStr = StringUtils.hasText(traceId) ? traceId : UuidUtils.getUuid();
        MDC.put(LOG_TRACK_ID_MARK, traceIdStr);
    }

    /**
     * 从日志上下文中移除 TraceId
     *
     * @return traceId
     */
    public static void removeTraceId() {
        MDC.remove(LOG_TRACK_ID_MARK);
    }

}
