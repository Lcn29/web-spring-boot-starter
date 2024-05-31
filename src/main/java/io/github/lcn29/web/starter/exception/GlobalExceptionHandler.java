package io.github.lcn29.web.starter.exception;

import io.github.lcn29.web.starter.code.WebBaseErrorStatusCode;
import io.github.lcn29.web.starter.constant.StringConstants;
import io.github.lcn29.web.starter.response.Response;
import io.github.lcn29.web.starter.response.ResponseBuilder;
import jakarta.servlet.ServletException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * 全局异常捕获
 *
 * @author lcn29
 * @date 2023-03-05 15:14
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * http 请求方式错误/请求参数缺失
     *
     * @param exception 异常信息
     * @return 响应结果
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {HttpRequestMethodNotSupportedException.class, MissingServletRequestParameterException.class})
    public Response<Void> missingServletRequestParameterException(ServletException exception) {

        logger.info("Http Request Method Exception:{}", exception.getMessage());
        return ResponseBuilder.fail(WebBaseErrorStatusCode.PARAM_ERROR.getCode(), WebBaseErrorStatusCode.PARAM_ERROR.getMessage());
    }

    /**
     * http 请求参数匹配错误
     *
     * @param exception 异常信息
     * @return 响应结果
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public Response<Void> conversionFailedException(MethodArgumentTypeMismatchException exception) {

        logger.info("Http Request Param Convert Exception:{}", exception.getMessage());
        return ResponseBuilder.fail(WebBaseErrorStatusCode.PARAM_ERROR.getCode(),
            StringUtils.hasLength(exception.getMessage()) ? exception.getMessage() : WebBaseErrorStatusCode.PARAM_ERROR.getMessage());
    }

    /**
     * validation 参数校验异常
     *
     * @param exception 异常信息
     * @return 响应结果
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {MethodArgumentNotValidException.class, BindException.class})
    public Response<Void> bindException(BindException exception) {

        logger.info("Http Request Param Valid Exception:{}", exception.getMessage());
        return ResponseBuilder.fail(WebBaseErrorStatusCode.PARAM_ERROR.getCode(), buildMessages(exception.getBindingResult()));
    }

    /**
     * 业务 info 异常
     *
     * @param exception 异常信息
     * @return 响应结果
     */
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    @ExceptionHandler(InfoException.class)
    public Response<Void> infoException(InfoException exception) {
        logger.info("Business's InfoException:{}", exception.getMessage());
        return ResponseBuilder.fail(exception.getCode(), exception.getMessage(), null);
    }

    /**
     * 业务 warn 异常
     *
     * @param exception 异常信息
     * @return 响应结果
     */
    @ExceptionHandler(WarnException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    Response<Void> warnException(WarnException exception) {

        logger.warn("Business's WarnException:{}", exception.getMessage(), exception);
        return ResponseBuilder.fail(exception.getCode(), exception.getMessage());
    }

    /**
     * 最终兜底的异常, Exception
     *
     * @param exception 异常信息
     * @return 响应结果
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    Response<Void> defaultException(Exception exception) {

        logger.warn("System's Exception:{}", exception.getMessage(), exception);
        return ResponseBuilder.fail(WebBaseErrorStatusCode.INTERNAL_SERVER_ERROR.getCode(),
            WebBaseErrorStatusCode.INTERNAL_SERVER_ERROR.getMessage());
    }

    /**
     * 构建错误信息
     *
     * @param result 错误异常信息
     * @return 错误信息字符串
     */
    private String buildMessages(BindingResult result) {

        if (CollectionUtils.isEmpty(result.getAllErrors())) {
            return StringConstants.EMPTY_STR;
        }

        StringBuilder resultBuilder = new StringBuilder();
        // 拼接字符串
        for (ObjectError allError : result.getAllErrors()) {
            if (allError instanceof FieldError fieldError) {
                resultBuilder.append(fieldError.getField()).append(StringConstants.BLANK_SPACE)
                    .append(fieldError.getDefaultMessage())
                    .append(StringConstants.SEMICOLON)
                    .append(StringConstants.BLANK_SPACE);
            }
        }
        return resultBuilder.toString();
    }
}
