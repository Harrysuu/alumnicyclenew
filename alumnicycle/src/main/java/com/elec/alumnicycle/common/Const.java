package com.elec.alumnicycle.common;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Company: www.southgis.com</p>
 *
 * @author Stephen
 * @version 1.0, created at 2018/12/18 11:53
 */
public class Const {

    /**
     * 请求成功标志码
     */
    public static final int SUCCESS = 1;
    /**
     * 请求失败标志码
     */
    public static final int FAIL = 0;
    /**
     * 请求成功默认返回消息
     */
    public static final String SUCCESS_MSG = "操作成功";
    /**
     * 请求失败默认返回消息
     */
//    public static final String FAIL_MSG = "操作失败";
    public static final String FAIL_MSG = "网络不稳定，请检查网络环境或切换网络！";
    /**
     * 请求失败默认返回消息
     */
//    public static final String APPLICATION_ERROR_MSG = "系统内部出错，请与管理员联系！";
    public static final String APPLICATION_ERROR_MSG = "网络不稳定，请检查网络环境或切换网络！";
    /**
     * 缓存超时时间
     */
    public static final int CACHE_LIVE_TO_SEC = 60 * 10;
    /**
     * 字典缓存
     */
    public static final String CACHE_DICT = "dictCache";
    /**
     * 默认返回参数的名称
     */
    public static final String DEFAULT_RETURN_NAME = "data";

    /**
     * 冒号
     */
    public static final String COLON = ":";

    /**
     * 反斜杠
     */
    public static final String BLACKSLASH = "/";

    /**
     * 下划线
     */
    public static final String UNDERSCORE = "_";

    /**
     * 破折线
     */
    public static final String BROKENLINE = "-";

    /**
     * http请求前缀
     */
    public static final String HTTP_PREFIX = "http://";

    /**
     * http请求类型(form请求)
     */
    public static final String X_WWWW_FORM_URLENCODED = "application/x-www-form-urlencoded";

    /**
     * http请求类型(JSON)
     */
    public static final String JSON = "application/json";

    /**
     * 构造服务ip
     */
    public static String getServerIp(String ip, Integer port) {
        String serverIp = HTTP_PREFIX + ip + COLON + port + BLACKSLASH;
        return serverIp;
    }

    private Const() {
    }

}
