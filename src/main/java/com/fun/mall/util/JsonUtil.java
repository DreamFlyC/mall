package com.fun.mall.util;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * @ Author     ：CZP.
 * @ Date       ：Created in 8:43 2018/12/10
 * @ Description：JSON工具类
 * @ Modified By：
 * @ Version: 1.0$
 */
public class JsonUtil {
    private static final Logger log = LoggerFactory.getLogger(JsonUtil.class);

    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        // 对象的所有字段全部列入
        objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);

        //取消默认转换timestamp格式
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        //忽略空bean转json的错误,不抛出异常
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        //转换日期格式
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

        //忽略在json字符串中存在，但是在java对象中不存在对应属性的情况，防止错误
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    }

    public static <T> String objToString(T obj) {
        if (obj == null) {
            return null;
        }
        try {
            return obj instanceof String ? (String) obj : objectMapper.writeValueAsString(obj);
        } catch (IOException e) {
            log.warn("obj转String出现异常", e);
            return null;
        }
    }

    public static <T> String objToStringPretty(T obj) {
        if (obj == null) {
            return null;
        }
        try {
            return obj instanceof String ? (String) obj : objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (IOException e) {
            log.warn("obj转String出现异常", e);
            return null;
        }
    }

    public static <T> T stringToObj(String str, Class<T> clazz) {
        if (StringUtils.isEmpty(str) || clazz == null) {
            return null;
        }
        try {
            return clazz.equals(String.class) ? (T) str : objectMapper.readValue(str, clazz);
        } catch (IOException e) {
            log.warn("String 转 obj出现异常", e);
            return null;
        }
    }

    public static <T> T stringToObj(String str, TypeReference<T> typeReference) {
        if (StringUtils.isEmpty(str) || typeReference == null) {
            return null;
        }
        try {
            return (T) (typeReference.getType().equals(String.class) ? str : objectMapper.readValue(str, typeReference));
        } catch (IOException e) {
            log.warn("String 转 obj出现异常", e);
            return null;
        }
    }

    public static <T> T stringToObj(String str, Class<?> collectionClass, Class<?>... elementClass) {
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClass);
        try {
            return objectMapper.readValue(str, javaType);
        } catch (IOException e) {
            log.warn("String 转 obj出现异常", e);
            return null;
        }
    }

    public static void main(String[] args) {
        File file = new File("C:\\Users\\CZP\\.m2\\repository\\com\\csii\\netbank");
        fileList(file);
    }

    //全路径输出方法
    public static void fileList(File file) {
        File[] files = file.listFiles();
        if (files != null) {
            for (File f : files) {
                /*if ("resolver-status.properties".equals(f.getName())) {
                    boolean delete = f.delete();
                    if(delete){
                        System.out.println(f.getPath()+"删除成功");
                    }else {
                        System.out.println(f.getPath()+"删除失败！！！");
                    }
                }*/

                fileList(f);
            }
        }
    }

}
