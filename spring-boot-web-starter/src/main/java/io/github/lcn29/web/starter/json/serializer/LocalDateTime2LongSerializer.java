package io.github.lcn29.web.starter.json.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import io.github.lcn29.web.starter.util.DateTimeUtils;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * <pre>
 * 序列化器: 将 LocalDateTime 序列化为 Long
 * </pre>
 *
 * @author lcn29
 * @date 2024-05-05 16:53:29
 */
public class LocalDateTime2LongSerializer extends JsonSerializer<LocalDateTime> {

    @Override
    public void serialize(LocalDateTime localDateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (localDateTime != null && jsonGenerator != null) {
            jsonGenerator.writeNumber(DateTimeUtils.localDateTime2Long(localDateTime));
        }
    }

}
