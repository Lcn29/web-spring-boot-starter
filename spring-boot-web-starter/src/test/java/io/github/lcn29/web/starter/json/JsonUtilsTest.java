package io.github.lcn29.web.starter.json;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * JsonUtils 测试
 *
 * @author lcn29
 * @date 2024-05-05 16:02:49
 */
public class JsonUtilsTest {

    @Test
    public void testToString() {
        // 1 object test
        JsonData jsonData = new JsonData();
        jsonData.setName("lcn29");
        jsonData.setAge(18);

        InnerJsonData innerJsonData = new InnerJsonData();
        innerJsonData.setInnerString("innerString");
        jsonData.setInnerJsonData(innerJsonData);

        System.out.println(JsonUtils.toString(jsonData));

        // 2 list test
        int listSize = 3;
        List<JsonData> list = new ArrayList<>(listSize);
        for (int i = 0; i < listSize; i++) {
            JsonData data = new JsonData();
            data.setName("lcn29" + i);
            data.setAge(18 + i);
            data.setInnerJsonData(innerJsonData);
            list.add(data);
        }

        System.out.println(JsonUtils.toString(list));
    }

    @Test
    public void testToObj() {

        String json = "{\"name\":\"lcn29\",\"age\":18,\"innerJsonData\":{\"innerString\":\"innerString\"}}";
        JsonData jsonData = JsonUtils.toObj(json, JsonData.class);
        System.out.println(jsonData.toString());
    }

    @Test
    public void testToObjList() {
        String json = "[{\"name\":\"lcn29\",\"age\":18,\"innerJsonData\":{\"innerString\":\"innerString\"}}]";
        List<JsonData> list = JsonUtils.toObjList(json, JsonData.class);
        list.forEach(System.out::println);
    }

    public static class JsonData {
        private String name;

        private Integer age;

        private InnerJsonData innerJsonData;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public InnerJsonData getInnerJsonData() {
            return innerJsonData;
        }

        public void setInnerJsonData(InnerJsonData innerJsonData) {
            this.innerJsonData = innerJsonData;
        }

        @Override
        public String toString() {
            return "JsonData(name=" + this.getName() + ", age=" + this.getAge() + ", innerJsonData=" + this.getInnerJsonData() + ")";
        }
    }

    public static class InnerJsonData {
        private String innerString;

        public String getInnerString() {
            return innerString;
        }

        public void setInnerString(String innerString) {
            this.innerString = innerString;
        }

        @Override
        public String toString() {
            return "InnerJsonData(innerString=" + this.getInnerString() + ")";
        }
    }
}
