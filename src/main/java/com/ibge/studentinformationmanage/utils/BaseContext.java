package com.ibge.studentinformationmanage.utils;

/**
 * 工具类ThreadLocal,每个线程的用户id
 */
public class BaseContext {
    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();
    public static void setCurrentId(String id){
        threadLocal.set(id);
    }
    public static String getCurrentId(){
        return threadLocal.get();
    }
}
