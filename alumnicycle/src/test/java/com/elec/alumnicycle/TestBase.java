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
 * @Description: mock 测试基础类 所有的测试类都需要继承当前的类
 * @version: 1.0
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

        // 获取类上的注解
        RequestMapping annotation = AnnotationUtils.findAnnotation(targetCLass, RequestMapping.class);
        // path 的前缀
        String path = annotation.path()[0];
        // 获取所有的方法
        Method[] methods = targetCLass.getMethods();

        String requestType = "get";
        for (Method method : methods) {

            if (method.getName().equals(methodName)) {
                // 获取当前方法上的参数
                Parameter[] parameters = method.getParameters();

                // Get 请求
                if(AnnotatedElementUtils.hasAnnotation(method, GetMapping.class)){

                    GetMapping getMapping = method.getAnnotation(GetMapping.class);

                    path =  path.concat(getMapping.value()[0]);

                    requestType = "get";

                }
                // post 请求
                if(AnnotatedElementUtils.hasAnnotation(method, PostMapping.class)){

                    PostMapping postMapping = method.getAnnotation(PostMapping.class);
                    path =  path.concat(postMapping.value()[0]);

                    requestType="post";
                }

                // 获取方法名称
                String name = method.getName();

                // 获取方法的返回值 类型的全路径名
                Class<?> returnType = method.getReturnType();

                HashMap<String,Object> param= new HashMap<>();

                HashMap<String, Object> sessionAttributes= new HashMap<>();

                HashMap<String,Object> requestBodyParam = new HashMap<>();
                if(parameters !=null && parameters.length > 1){

                    for(Parameter parameter : parameters){

                        // 获取 参数名
                        String parameterName = parameter.getName();

                        // 获取参数类型
                        Class<?> type = parameter.getType();

                        // 参数被 @requestBody 标记
                        if(AnnotatedElementUtils.hasAnnotation(parameter, RequestBody.class)){

                            requestBodyParam.put(parameterName,type);
                            continue;
                        }
                        // 参数被 @requestParam 标记
                        if(AnnotatedElementUtils.hasAnnotation(parameter, RequestParam.class)){
                            param.put(parameterName,type);
                            continue;
                        }
                        // 参数类型是否是  HttpServletRequest
                        if(HttpServletRequest.class.isAssignableFrom(type)){

                            sessionAttributes.put(parameterName,type);
                            continue;
                        }
                        // 其他的放到 param 中
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
        // 设置参数 requestParam / input
        if(methodInfo.isHasParam() && param !=null && !param.isEmpty()){
            param.keySet().forEach(k ->{

                requestBuilder.param(k,param.get(k).toString());
            });
        }

        Map<String, Object> sessionAttributes = methodInfo.getSessionAttributes();
        // 设置 session
        if(methodInfo.isHasSession() && sessionAttributes !=null && !sessionAttributes.isEmpty()){
            requestBuilder.sessionAttrs(sessionAttributes);

        }

        Map<String, Object> requestBodyParam = methodInfo.getRequestBodyParam();

        // 设置参数 requestBody
        if(methodInfo.isHasRequestBody() && requestBodyParam !=null && !requestBodyParam.isEmpty()){

            requestBuilder.content(JSON.toJSON(requestBodyParam).toString());
        }
        // mock调用
        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        return JSONObject.parseObject(mvcResult.getResponse().getContentAsString(), methodInfo.getReturnType());
    }





    private MockHttpServletRequestBuilder  requestBuilder(TestMethodInfo methodInfo){

        String requestType = methodInfo.getRequestType();
        // 添加调用的接口路径
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
