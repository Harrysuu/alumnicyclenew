package com.elec.alumnicycle;

import lombok.Builder;
import lombok.Data;

import java.util.HashMap;


@Data
@Builder
public class TestMethodInfo {


    private String methodName;

    private String path;

    // get  post
    private String requestType;

    private boolean hasParam;
    private HashMap<String,Object> param;

    private boolean hasSession;
    private HashMap<String, Object> sessionAttributes;

    private boolean hasRequestBody;
    private  HashMap<String,Object> requestBodyParam;

    private Class<?> returnType;




}
