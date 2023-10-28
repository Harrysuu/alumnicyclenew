package com.elec.alumnicycle;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.elec.alumnicycle.common.AjaxRes;
import com.elec.alumnicycle.controller.UserController;
import com.elec.alumnicycle.entity.User;
import org.junit.Assert;
import org.junit.Test;
import java.util.HashMap;
import java.util.Map;

public class UserTest extends TestBase {

    @Test
    public void testUniqueUsername() throws Exception {

        TestMethodInfo methodInfo = getMethodInfo(UserController.class, "uniqueUsername");
        Assert.assertNotNull(methodInfo);
        HashMap<String,Object > param = methodInfo.getParam();

        methodInfo.setHasParam(true);
        param.put("username","Alice20236");

        Object ajaxRes = mock(methodInfo);

        Assert.assertEquals("username not unique",true,ajaxRes);

    }

    @Test
    public void testGetUser() throws Exception {

        TestMethodInfo methodInfo = getMethodInfo(UserController.class, "getUserById");

        Assert.assertNotNull(methodInfo);
        HashMap<String,Object > param = methodInfo.getParam();

        methodInfo.setHasParam(true);
        param.put("userId","99");

        AjaxRes ajaxRes = (AjaxRes) mock(methodInfo);



    }
    @Test
    public void testAddCredit() throws Exception {

        TestMethodInfo methodInfo = getMethodInfo(UserController.class, "getUserById");

        Assert.assertNotNull(methodInfo);
        HashMap<String,Object > param = methodInfo.getParam();

        methodInfo.setHasParam(true);
        param.put("userId","99");

        AjaxRes ajaxRes = (AjaxRes) mock(methodInfo);

        Double OldCredit = (JSON.parseObject(ajaxRes.getResult().toString(),User.class) ).getCredit();

        methodInfo = getMethodInfo(UserController.class, "addCredit");
        Assert.assertNotNull(methodInfo);

        Double addValue = new Double("100");
        param =  methodInfo.getParam();
        methodInfo.setHasParam(true);
        param.put("point",addValue.toString());
        Map<String, Object> sessionAttributes = methodInfo.getSessionAttributes();
        methodInfo.setHasSession(true);
        sessionAttributes.put("User",99L);
         ajaxRes = (AjaxRes) mock(methodInfo);
        Double newCredit =(JSON.parseObject(ajaxRes.getResult().toString(),User.class) ).getCredit();

        Double except =Double.valueOf( OldCredit + addValue);
        Assert.assertEquals("add credit fail:::",except  ,newCredit);

    }

    @Test
    public void testLoginByPhone() throws Exception {

        TestMethodInfo methodInfo = getMethodInfo(UserController.class, "loginWithPhone");
        Assert.assertNotNull(methodInfo);
        methodInfo.setHasParam(true);
        HashMap<String, Object> param = methodInfo.getParam();

        param.put("phoneNumber","100");
        methodInfo.setHasSession(true);
        Map<String, Object> sessionAttributes = methodInfo.getSessionAttributes();
        sessionAttributes.put("User",99L);
        AjaxRes ajaxRes = (AjaxRes) mock(methodInfo);
        Assert.assertEquals("login:::", "Login success", ajaxRes.getResult());

    }


    @Test
    public void testUpdateUser() throws Exception {

        TestMethodInfo methodInfo = getMethodInfo(UserController.class, "updateUser");
        Assert.assertNotNull(methodInfo);
//        HttpServletRequest request, @RequestBody User user
        methodInfo.setHasSession(true);
        Map<String, Object> sessionAttributes = methodInfo.getSessionAttributes();
        sessionAttributes.put("User",99L);
        HashMap<String, Object> requestBodyParam = methodInfo.getRequestBodyParam();

        methodInfo.setHasRequestBody(true);

        requestBodyParam.put("description","3333");
        AjaxRes ajaxRes = (AjaxRes) mock(methodInfo);

        User user = JSONObject.parseObject(ajaxRes.getResult().toString(), User.class);

        Assert.assertEquals("update user :::", "3333", user.getDescription());

    }









}
