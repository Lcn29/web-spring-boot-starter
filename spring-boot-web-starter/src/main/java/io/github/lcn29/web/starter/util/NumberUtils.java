package io.github.lcn29.web.starter.util;

/**
 * Number Utils
 *
 * @author lcn29
 * @date 2024-05-05 16:34:20
 */
public class NumberUtils extends org.springframework.util.NumberUtils {


    /**
     * 判断一个字符串是否为数字
     *
     * @param str 需要判断的字符串
     * @return true: 是数字, false: 不是
     */
    public static Boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        return str.chars().allMatch(Character::isDigit);
    }
}
