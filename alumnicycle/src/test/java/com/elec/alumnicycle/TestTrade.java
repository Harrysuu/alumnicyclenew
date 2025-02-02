package com.elec.alumnicycle;

import com.alibaba.fastjson.JSONObject;
import com.elec.alumnicycle.common.AjaxRes;
import com.elec.alumnicycle.controller.TradeController;
import com.elec.alumnicycle.entity.Trade;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

public class TestTrade extends TestBase{



    @Test
    public void getTradeById() throws Exception {

        TestMethodInfo methodInfo = getMethodInfo(TradeController.class, "getTradeById");
        Assert.assertNotNull(methodInfo);
        HashMap<String,Object > param = methodInfo.getParam();


        methodInfo.setHasParam(true);
        param.put("tradeId","1717369665313918977");
        AjaxRes ajaxRes = (AjaxRes) mock(methodInfo);

        Trade trade = JSONObject.parseObject(ajaxRes.getResult().toString(), Trade.class);

        Assert.assertEquals("check trade info ","1717369665313918977",trade.getId().toString());

    }





}
