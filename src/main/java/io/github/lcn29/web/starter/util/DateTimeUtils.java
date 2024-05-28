package io.github.lcn29.web.starter.util;

import io.github.lcn29.web.starter.constant.StringConstants;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * Date Time Utils
 *
 * @author lcn29
 * @date 2024-05-05 16:40:06
 */
public class DateTimeUtils {

    /**
     * 日期格式类型
     */
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(StringConstants.DATE_FORMAT);

    /**
     * 日期格式类型
     */
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(
        StringConstants.DATE_TIME_FORMAT);


    /**
     * long 转 localDate
     *
     * @param longTime long 时间
     * @return localDate
     */
    public static LocalDate long2LocalDate(long longTime) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(longTime), ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * long 转 localDateTime
     *
     * @param longTime long 时间
     * @return localDateTime
     */
    public static LocalDateTime long2LocalDateTime(Long longTime) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(longTime), ZoneId.systemDefault());
    }

    /**
     * 字符串转 localDate
     *
     * @param stringDate 需要转化的字符串
     * @return localDate
     */
    public static LocalDate string2LocalDate(String stringDate) {
        return LocalDate.parse(stringDate, DATE_FORMATTER);
    }

    /**
     * 字符串转 localDateTime
     *
     * @param stringDate 需要转化的字符串
     * @return localDateTime
     */
    public static LocalDateTime string2LocalDateTime(String stringDate) {
        return LocalDateTime.parse(stringDate, DATE_TIME_FORMATTER);
    }


    /**
     * localDate  转 long
     *
     * @param localDate localDate  时间
     * @return 转换后的 long 类型时间戳
     */
    public static Long localDate2Long(LocalDate localDate) {
        return localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * localDate  转 long
     *
     * @param localDateTime localDate  时间
     * @return 转换后的 long 类型时间戳
     */
    public static Long localDateTime2Long(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }
}
