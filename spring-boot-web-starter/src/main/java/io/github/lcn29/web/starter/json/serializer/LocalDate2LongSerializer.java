package io.github.lcn29.web.starter.json.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import io.github.lcn29.web.starter.util.DateTimeUtils;

import java.io.IOException;
import java.time.LocalDate;

/**
 * <pre>
 * 序列化器: 将 LocalDate 序列化为 Long
 * </pre>
 *
 * @author lcn29
 * @date 2024-05-05 16:53:29
 */
public class LocalDate2LongSerializer extends JsonSerializer<LocalDate> {

    @Override
    public void serialize(LocalDate localDate, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (localDate != null && jsonGenerator != null) {
            jsonGenerator.writeNumber(DateTimeUtils.localDate2Long(localDate));
        }
    }

}
