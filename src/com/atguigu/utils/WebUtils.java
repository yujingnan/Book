package com.atguigu.utils;

import com.atguigu.pojo.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class WebUtils {
    public  static <T> T copyParamToBean(Map value, T bean){
        //使用BeanUtils工具类注入参数
        try {

            BeanUtils.populate(bean,value);
            System.out.println("注入之后"+bean);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return bean;
    }
    public static int parseInt(String string,Integer defaultValue ){
        try {
            return Integer.parseInt(string);
        } catch (Exception e) {
//            e.printStackTrace();
        }
        return defaultValue;
    }
}
