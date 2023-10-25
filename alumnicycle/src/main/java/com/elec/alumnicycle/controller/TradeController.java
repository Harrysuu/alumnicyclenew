package com.elec.alumnicycle.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.elec.alumnicycle.common.AjaxRes;
import com.elec.alumnicycle.entity.Trade;
import com.elec.alumnicycle.entity.params.TradeParam;
import com.elec.alumnicycle.service.TradeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/trade")
@Slf4j
@Api(tags = "Trade Page ")
public class TradeController {
    @Autowired
    private TradeService tradeService;
    @PostMapping("/page")
    @ApiOperation(value = "get trade pages")
    public AjaxRes<Page<Trade>> tradePage(@RequestBody TradeParam param){
        return tradeService.getTradeByPage(param);
    }

    @GetMapping("/getById")
    @ApiOperation(value = "get trade by tradeId")
    public AjaxRes<Trade> getTradeById(Long tradeId){
        return tradeService.getTradeById(tradeId);
    }
    @GetMapping("/submit")
    public AjaxRes<String> submit(HttpServletRequest request){
        return tradeService.submit(request);
    }
}
