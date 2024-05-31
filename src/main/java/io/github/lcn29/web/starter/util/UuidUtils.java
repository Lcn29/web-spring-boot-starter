package io.github.lcn29.web.starter.util;

import io.github.lcn29.web.starter.constant.StringConstants;

import java.util.UUID;

/**
 * UUID 工具类
 *
 * @author lcn29
 * @date 2024-05-05 17:29:25
 */
public class UuidUtils {


    /**
     * 获取 UUID
     *
     * @return UUID
     */
    public static String getUuid() {
        return UUID.randomUUID().toString().replace(StringConstants.CROSS_BAR, StringConstants.EMPTY_STR);
    }
}
