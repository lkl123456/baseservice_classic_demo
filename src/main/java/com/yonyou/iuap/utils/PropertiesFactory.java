package com.yonyou.iuap.utils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Properties;

/**
 * @author admin
 * 属性文件工厂类
 */
public class PropertiesFactory {

    public static final String CONFIG_PROPERTIES = "config.properties";

    private static HashMap<String, Properties> propertiesMap = new HashMap<String, Properties>();

    private PropertiesFactory(){}
    
    /**
     * 根据filename, key获取配置
     * @param filename - 配置文件名字
     * @param key - properties key
     * @return
     */
    public static String getProperty(String filename, String key) {
    	Properties properties = getProperties(filename);
    	if (properties == null) {
    		return null;
    	}
    	return properties.getProperty(key);
    }

    /**
     * 根据filename 获取Properties对象
     * @param filename
     * @return
     */
    public static Properties getProperties(String filename){
        Properties props = propertiesMap.get(filename);
        if(null == props) {
            props = new Properties();
            InputStreamReader in = null;
            try{
                in = new InputStreamReader(PropertiesFactory.class.getResourceAsStream("/" + filename), "utf-8");
                props.load(in);
                propertiesMap.put(filename, props);
                return props;

            } catch (Exception e) {
            }finally{
                if (in != null){
                    try {
                        in.close();
                    } catch (IOException e) {
                    }
                }
            }
        }
        return props;
    }

    public static void main(String[] args) {
    	String value=getProperty("application.properties", "jdbc.url");
    	System.out.println(value);
	}
}
