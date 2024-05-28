package io.github.lcn29.web.starter.exception;


import io.github.lcn29.web.starter.code.ErrorStatusCode;

/**
 * <pre>
 * 业务异常
 * 全局捕获后, 会打印 info 日志级别, 同时只打印 message 信息, 不包含堆栈信息
 * </pre>
 *
 * @author lcn29
 * @date 2024-05-05 17:15
 */
public class InfoException extends RuntimeException {

    private final int code;

    private final String message;

    public InfoException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public InfoException(ErrorStatusCode statusCodeInterface) {
        super(statusCodeInterface.getMessage());
        this.code = statusCodeInterface.getCode();
        this.message = statusCodeInterface.getMessage();
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
