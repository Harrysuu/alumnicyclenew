package com.elec.alumnicycle;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson2.JSON;
import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: mock base for all tests
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
@AutoConfigureMockMvc
public class TestBase {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private  MockMvc mockMvc;

    @Before()
    public void setup () {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    public TestMethodInfo  getMethodInfo(Class<?> targetCLass,String methodName) {

        // Get annotations on a class
        RequestMapping annotation = AnnotationUtils.findAnnotation(targetCLass, RequestMapping.class);
        // prefix of path
        String path = annotation.path()[0];
        // Get all methods
        Method[] methods = targetCLass.getMethods();

        String requestType = "get";
        for (Method method : methods) {

            if (method.getName().equals(methodName)) {
                // Get the parameters on the current method
                Parameter[] parameters = method.getParameters();

                // Get request
                if(AnnotatedElementUtils.hasAnnotation(method, GetMapping.class)){

                    GetMapping getMapping = method.getAnnotation(GetMapping.class);

                    path =  path.concat(getMapping.value()[0]);

                    requestType = "get";

                }
                // post request
                if(AnnotatedElementUtils.hasAnnotation(method, PostMapping.class)){

                    PostMapping postMapping = method.getAnnotation(PostMapping.class);
                    path =  path.concat(postMapping.value()[0]);

                    requestType="post";
                }

                // Get method name
                String name = method.getName();

                // Get the full path name of the return value type of the method
                Class<?> returnType = method.getReturnType();

                HashMap<String,Object> param= new HashMap<>();

                HashMap<String, Object> sessionAttributes= new HashMap<>();

                HashMap<String,Object> requestBodyParam = new HashMap<>();
                if(parameters !=null && parameters.length > 1){

                    for(Parameter parameter : parameters){

                        // Get parameter name
                        String parameterName = parameter.getName();

                        // Get parameter type
                        Class<?> type = parameter.getType();

                        // Parameters are marked with @requestBody
                        if(AnnotatedElementUtils.hasAnnotation(parameter, RequestBody.class)){

                            requestBodyParam.put(parameterName,type);
                            continue;
                        }
                        // Parameters are marked with @requestParam
                        if(AnnotatedElementUtils.hasAnnotation(parameter, RequestParam.class)){
                            param.put(parameterName,type);
                            continue;
                        }
                        // Whether the parameter type is HttpServletRequest
                        if(HttpServletRequest.class.isAssignableFrom(type)){

                            sessionAttributes.put(parameterName,type);
                            continue;
                        }
                        // Put the others in param
                        param.put(parameterName,type);

                    }
                }

                return TestMethodInfo.builder()
                        .methodName(name)
                        .path(path)
                        .requestType(requestType)
                        .returnType(returnType)
                        .param(param)
                        .requestBodyParam(requestBodyParam)
                        .sessionAttributes(sessionAttributes)
                        .build();

            }
        }
        return null;
    }



    public Object mock(TestMethodInfo methodInfo) throws Exception {

        MockHttpServletRequestBuilder requestBuilder = requestBuilder(methodInfo);

        Assert.assertNotNull(requestBuilder);

        Map<String, Object> param = methodInfo.getParam();
        // Set parameters requestParam/input
        if(methodInfo.isHasParam() && param !=null && !param.isEmpty()){
            param.keySet().forEach(k ->{

                requestBuilder.param(k,param.get(k).toString());
            });
        }

        Map<String, Object> sessionAttributes = methodInfo.getSessionAttributes();
        // set session
        if(methodInfo.isHasSession() && sessionAttributes !=null && !sessionAttributes.isEmpty()){
            requestBuilder.sessionAttrs(sessionAttributes);

        }

        Map<String, Object> requestBodyParam = methodInfo.getRequestBodyParam();

        // set requestBody
        if(methodInfo.isHasRequestBody() && requestBodyParam !=null && !requestBodyParam.isEmpty()){

            requestBuilder.content(JSON.toJSON(requestBodyParam).toString());
        }
        // use mock
        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        return JSONObject.parseObject(mvcResult.getResponse().getContentAsString(), methodInfo.getReturnType());
    }


    private MockHttpServletRequestBuilder  requestBuilder(TestMethodInfo methodInfo){

        String requestType = methodInfo.getRequestType();
        // Add the calling interface path
        MockHttpServletRequestBuilder requestBuilder = null;
        if("get".equalsIgnoreCase(requestType)){

            requestBuilder = MockMvcRequestBuilders
                    .get(methodInfo.getPath())
                    .contentType(MediaType.APPLICATION_JSON);
        }
        if("post".equalsIgnoreCase(requestType)){

            requestBuilder =  MockMvcRequestBuilders
                    .post(methodInfo.getPath())
                    .contentType(MediaType.APPLICATION_JSON);
        }
        return requestBuilder;
    }

}
