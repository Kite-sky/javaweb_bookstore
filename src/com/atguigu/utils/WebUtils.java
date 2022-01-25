package com.atguigu.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;

/**
 * 把Map中的值注入到对应的JavaBean属性中。
 * @description：TODO
 * @date ：2022/1/19 15:43
 */
public class WebUtils {
    public static <T> T copyParamToBean(Map value, Object bean) {
        try {
            System.out.println("注入之前："+bean);
            /**
             * 把所有请求的参数都注入到bean 对象中,可以省去大量的req.getParameter()方法
             */
            BeanUtils.populate(bean,value);
            System.out.println("注入之后："+bean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (T) bean;
    }

    /**
     * 将字符串转换成为int类型的数据
     * @param strInt
     * @param defaultValue
     * @return
     */
    public static int parseInt(String strInt,int defaultValue) {
        try {
            return Integer.parseInt(strInt);
        } catch (Exception e) {
//            e.printStackTrace();
        }
        return defaultValue;
    }
}
