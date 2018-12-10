package com.fun.mall.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/*
 * create by: CZP
 * description:配置文件工具类
 * create time: 14:44 2018/12/5
 * @return 
 */
public class PropertiesUtil {

    private static Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);

    private static Properties props;

    static {
        String fileName = "mall.properties";
        props = new Properties();
        try {
            InputStreamReader inputStreamReader=new InputStreamReader(PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName),"UTF-8");
            props.load(inputStreamReader);
        } catch (IOException e) {
            logger.error("配置文件读取异常",e);
        }finally {

        }
    }

    public static String getProperty(String key){
        String value = props.getProperty(key.trim());
        if(StringUtils.isBlank(value)){
            return null;
        }
        return value.trim();
    }

    public static String getProperty(String key,String defaultValue){

        String value = props.getProperty(key.trim());
        if(StringUtils.isBlank(value)){
            value = defaultValue;
        }
        return value.trim();
    }

    //加载redis配置文件
    public static Properties loadProperties(String propertyFile){
        Properties properties=new Properties();
        InputStream is=PropertiesUtil.class.getClassLoader().getResourceAsStream(propertyFile);
        try {
            if (is==null){
                is=PropertiesUtil.class.getClassLoader().getResourceAsStream("resources/"+propertyFile);
            }
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    /*
     * create by: CZP
     * description:根据key值取得对应的value值
     * create time: 14:39 2018/12/5
     * @return
     */
    public static String getValue(String propertyFile,String key){
        Properties properties=loadProperties(propertyFile);
        return properties.getProperty(key);
    }

}
