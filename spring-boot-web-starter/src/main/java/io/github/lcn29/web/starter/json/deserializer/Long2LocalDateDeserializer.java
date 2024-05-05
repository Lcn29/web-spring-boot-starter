package io.github.lcn29.web.starter.json.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import io.github.lcn29.web.starter.util.DateTimeUtils;

import java.io.IOException;
import java.time.LocalDate;

/**
 * <pre>
 * 反序列化器: 将 long 转为 localDate
 * </pre>
 *
 * @author lcn29
 * @date 2024-05-05 16:49:34
 */
public class Long2LocalDateDeserializer extends JsonDeserializer<LocalDate> {

    @Override
    public LocalDate deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException {
        if (jsonParser == null) {
            return null;
        }
        return DateTimeUtils.long2LocalDate(jsonParser.getValueAsLong());
    }
}
