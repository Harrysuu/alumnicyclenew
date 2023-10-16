package com.elec.alumnicycle.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.elec.alumnicycle.common.AjaxRes;
import com.elec.alumnicycle.entity.ShoppingCart;

import java.util.List;

public interface ShoppingCartService extends IService<ShoppingCart> {
    public AjaxRes<ShoppingCart> add (ShoppingCart shoppingCart);
    public AjaxRes<Page<ShoppingCart>>page();
    public AjaxRes<String> clean ();
}
