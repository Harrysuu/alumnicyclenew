package com.elec.alumnicycle;

import com.elec.alumnicycle.common.AjaxRes;
import com.elec.alumnicycle.controller.AdminController;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;


public class TestAdminUser  extends TestBase{

    @Test
    public void testUniqueUsername() throws Exception {

        TestMethodInfo methodInfo = getMethodInfo(AdminController.class, "uniqueUsername");

        Assert.assertNotNull(methodInfo);

        HashMap<String,Object > param = methodInfo.getParam();

        methodInfo.setHasParam(true);
        param.put("username","RachelZhang");


        Object ajaxRes = mock(methodInfo);

        Assert.assertEquals("username not unique",true,ajaxRes);


    }
    @Test
    public void testChangeUserStatus() throws Exception {

        TestMethodInfo methodInfo = getMethodInfo(AdminController.class, "changeUserStatus");

        Assert.assertNotNull(methodInfo);

        HashMap<String,Object > param = methodInfo.getParam();

        methodInfo.setHasParam(true);
        param.put("userId","1073741824000000008");


        AjaxRes ajaxRes = (AjaxRes) mock(methodInfo);

        Assert.assertEquals("change status","operate success",ajaxRes.getResMsg());


    }
    @Test
    public void testChangeAdminStatus() throws Exception {

        TestMethodInfo methodInfo = getMethodInfo(AdminController.class, "changeAdminStatus");

        Assert.assertNotNull(methodInfo);

        HashMap<String,Object > param = methodInfo.getParam();


        methodInfo.setHasParam(true);
        param.put("adminId","1073741824000000009");

        methodInfo.setHasSession(true);
        HashMap<String, Object> sessionAttributes = methodInfo.getSessionAttributes();

        sessionAttributes.put("Admin",1716621203541684226L);


        AjaxRes ajaxRes = (AjaxRes) mock(methodInfo);

        Assert.assertEquals("change admin status ","Unable to change the administrator status above current account level",ajaxRes.getResMsg());


    }


}
