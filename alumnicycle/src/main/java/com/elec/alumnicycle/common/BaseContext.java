package com.elec.alumnicycle.common;

/**
 * Set and get userId
 */
public class BaseContext {
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    /**
     * set
     * @param id
     */
    public static void setCurrentId(Long id){
        threadLocal.set(id);
    }

    /**
     * get
     *
     * @return
     */
    public static Long getCurrentId(){
        return threadLocal.get();
    }
}