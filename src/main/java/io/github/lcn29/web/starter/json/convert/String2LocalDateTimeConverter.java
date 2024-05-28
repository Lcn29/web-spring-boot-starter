package io.github.lcn29.web.starter.json.convert;

import io.github.lcn29.web.starter.util.DateTimeUtils;
import io.github.lcn29.web.starter.util.NumberUtils;
import org.springframework.core.convert.converter.Converter;

import java.time.LocalDateTime;

/**
 * String to local date time converter
 *
 * @author lcn29
 * @date 2024-05-05 16:45:40
 */
public class String2LocalDateTimeConverter implements Converter<String, LocalDateTime> {

    @Override
    public LocalDateTime convert(String source) {
        // 全都是数字, 尝试按照 long 进行处理
        if (NumberUtils.isNumeric(source)) {
            return DateTimeUtils.long2LocalDateTime(NumberUtils.parseNumber(source, Long.class));
        }
        // 不全都是数字, 尝试进行解析
        return DateTimeUtils.string2LocalDateTime(source);
    }

}
