package com.elec.alumnicycle.common;

public class Const {

    /**
     * Request success code
     */
    public static final int SUCCESS = 1;
    /**
     * Request failure flag code
     */
    public static final int FAIL = 0;
    /**
     * A successful request returns a message by default
     */
    public static final String SUCCESS_MSG = "operate success";
    /**
     * The default return message if the request fails
     */
//    public static final String FAIL_MSG = "操作失败";
    public static final String FAIL_MSG = "Internet error";
    /**
     * The default return message if the request fails
     */
//    public static final String APPLICATION_ERROR_MSG = "系统内部出错，请与管理员联系！";
    public static final String APPLICATION_ERROR_MSG = "Internet error";
    /**
     * Cache timeout
     */
    public static final int CACHE_LIVE_TO_SEC = 60 * 10;
    /**
     * dictionary cache
     */
    public static final String CACHE_DICT = "dictCache";
    /**
     * default return param name
     */
    public static final String DEFAULT_RETURN_NAME = "data";

    /**
     * :
     */
    public static final String COLON = ":";

    /**
     * /
     */
    public static final String BLACKSLASH = "/";

    /**
     * _
     */
    public static final String UNDERSCORE = "_";

    /**
     * -
     */
    public static final String BROKENLINE = "-";

    /**
     * http pre
     */
    public static final String HTTP_PREFIX = "http://";

    /**
     * http request type (form)
     */
    public static final String X_WWWW_FORM_URLENCODED = "application/x-www-form-urlencoded";

    /**
     * http request type (JSON)
     */
    public static final String JSON = "application/json";

    /**
     * construct service ip
     */

    public static String getServerIp(String ip, Integer port) {
        String serverIp = HTTP_PREFIX + ip + COLON + port + BLACKSLASH;
        return serverIp;
    }

    private Const() {
    }

}
