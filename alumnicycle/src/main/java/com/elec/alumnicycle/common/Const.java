package com.elec.alumnicycle.common;

public class Const {

    public static final int SUCCESS = 1;

    public static final int FAIL = 0;

    public static final String SUCCESS_MSG = "operate success";

    public static final String FAIL_MSG = "Internet error";

    public static final String APPLICATION_ERROR_MSG = "Internet error";

    public static final int CACHE_LIVE_TO_SEC = 60 * 10;

    public static final String CACHE_DICT = "dictCache";

    public static final String DEFAULT_RETURN_NAME = "data";

    public static final String COLON = ":";

    public static final String BLACKSLASH = "/";

    public static final String UNDERSCORE = "_";

    public static final String BROKENLINE = "-";

    public static final String HTTP_PREFIX = "http://";

    public static final String X_WWWW_FORM_URLENCODED = "application/x-www-form-urlencoded";

    public static final String JSON = "application/json";

    public static String getServerIp(String ip, Integer port) {
        String serverIp = HTTP_PREFIX + ip + COLON + port + BLACKSLASH;
        return serverIp;
    }

    private Const() {
    }

}
