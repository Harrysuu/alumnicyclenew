package com.elec.alumnicycle.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.elec.alumnicycle.common.AjaxRes;
import com.elec.alumnicycle.entity.Trade;
import com.elec.alumnicycle.entity.params.TradeParam;

import javax.servlet.http.HttpServletRequest;

public interface TradeService extends IService<Trade> {
    AjaxRes<Page<Trade>> getTradeByPage(TradeParam param);

    AjaxRes<Trade> getTradeById(Long tradeId);
    public AjaxRes<String> submit(HttpServletRequest request);
}
