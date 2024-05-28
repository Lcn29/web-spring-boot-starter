package io.github.lcn29.web.starter.code;

/**
 * Error Status Code
 *
 * @author lcn29
 * @date 2024-05-05 17:12:15
 */
public interface ErrorStatusCode {

    /**
     * 获取状态码
     *
     * @return 状态码
     */
    int getCode();

    /**
     * 获取状态描述
     *
     * @return 状态描述
     */
    String getMessage();
}
