package io.github.lcn29.web.starter.response;

import io.github.lcn29.web.starter.code.ErrorStatusCode;
import io.github.lcn29.web.starter.code.WebBaseErrorStatusCode;
import org.springframework.lang.Nullable;

/**
 * Response 构建器
 *
 * @author lcn29
 * @date 2024-05-05 17:20:47
 */
public class ResponseBuilder {

    /**
     * 构建不带响应内容的成功 Response
     *
     * @return Response
     */
    public static Response<Void> success() {
        return success(null);
    }

    /**
     * 构建带响应内容的成功 Response
     *
     * @param content 返回内容
     * @param <T>     返回内容的数据类型
     * @return Response
     */
    public static <T> Response<T> success(@Nullable T content) {
        return buildResponse(WebBaseErrorStatusCode.SUCCESS.getCode(), WebBaseErrorStatusCode.SUCCESS.getMessage(),
            content);
    }

    /**
     * 构建不带响应内容的失败 Response
     *
     * @param errorStatusCode 状态码接口
     * @return Response
     */
    public static Response<Void> fail(ErrorStatusCode errorStatusCode) {
        return fail(errorStatusCode.getCode(), errorStatusCode.getMessage(), null);
    }

    /**
     * 构建不带响应内容的失败 Response
     *
     * @param code    状态码
     * @param message 状态信息
     * @return Response
     */
    public static Response<Void> fail(int code, String message) {
        return fail(code, message, null);
    }

    /**
     * 构建带响应内容的失败 Response
     *
     * @param code    状态码
     * @param message 状态信息
     * @param content 返回内容
     * @param <T>     返回内容的数据类型
     * @return Response
     */
    public static <T> Response<T> fail(int code, String message, @Nullable T content) {
        return buildResponse(code, message, content);
    }

    /**
     * 构建带响应内容的失败 Response
     *
     * @param errorStatusCode 状态码接口
     * @param content         返回内容
     * @param <T>             返回内容的数据类型
     * @return Response
     */
    public static <T> Response<T> fail(ErrorStatusCode errorStatusCode, T content) {
        return buildResponse(errorStatusCode.getCode(), errorStatusCode.getMessage(), content);
    }

    /**
     * 构建 Response
     *
     * @param code    状态码
     * @param message 状态信息
     * @param content 返回内容
     * @param <T>     返回内容的数据类型
     * @return Response
     */
    private static <T> Response<T> buildResponse(int code, String message, T content) {
        return content == null ? new Response<T>(code, message) : new Response<T>(code, message, content);
    }

}
