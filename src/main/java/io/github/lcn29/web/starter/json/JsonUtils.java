package io.github.lcn29.web.starter.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.github.lcn29.web.starter.constant.StringConstants;
import io.github.lcn29.web.starter.json.exception.JsonParseException;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;

/**
 * Json Util
 *
 * @author lcn29
 * @date 2024-05-05 16:38:23
 */
public class JsonUtils {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {

        // 忽略对象的 null 值字段
        OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        // 设置 Date 类型的序列化及反序列化格式
        OBJECT_MAPPER.setDateFormat(new SimpleDateFormat(StringConstants.DATE_TIME_FORMAT));

        // 忽略空 Bean 转 json 的错误
        OBJECT_MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        // 忽略未知属性，防止 json 字符串中存在, java 对象中不存在对应属性的情况出现错误
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 注册一个时间序列化及反序列化的处理模块，用于解决 LocalDateTime 等的序列化问题
        OBJECT_MAPPER.registerModule(new JavaTimeModule());
    }

    /**
     * 对象 转 json字符串
     *
     * @param obj 源对象
     * @param <T> 泛型
     * @return json 字符串
     */
    @Nullable
    public static <T> String toString(T obj) {

        if (Objects.isNull(obj)) {
            return null;
        }

        try {
            return OBJECT_MAPPER.writeValueAsString(obj);
        } catch (Exception e) {
            throw new JsonParseException(e.getMessage());
        }
    }

    /**
     * json 字符串转对象
     *
     * @param json  源 json 串
     * @param clazz 对象类
     * @param <T>   泛型
     * @return 对象
     */
    @Nullable
    public static <T> T toObj(String json, Class<T> clazz) {

        if (!StringUtils.hasText(json)) {
            return null;
        }

        try {
            return OBJECT_MAPPER.readValue(json, clazz);
        } catch (Exception e) {
            throw new JsonParseException(e.getMessage());
        }
    }

    /**
     * json 字符串转对象
     *
     * @param json  源 json 串
     * @param clazz 对象类型
     * @param <T>   泛型
     * @return 对象
     */
    @Nullable
    public static <T> List<T> toObjList(String json, Class<T> clazz) {

        if (!StringUtils.hasText(json)) {
            return null;
        }

        try {
            CollectionType collectionType = OBJECT_MAPPER.getTypeFactory().constructCollectionType(List.class, clazz);
            return OBJECT_MAPPER.readValue(json, collectionType);
        } catch (Exception e) {
            throw new JsonParseException(e.getMessage());
        }
    }
}
