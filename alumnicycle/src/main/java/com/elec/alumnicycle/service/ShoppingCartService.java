package com.elec.alumnicycle.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.elec.alumnicycle.common.AjaxRes;
import com.elec.alumnicycle.entity.ShoppingCart;
import com.elec.alumnicycle.entity.params.ShoppingCartByIdParam;
import com.elec.alumnicycle.entity.params.ShoppingCartParam;
import javax.servlet.http.HttpServletRequest;

public interface ShoppingCartService extends IService<ShoppingCart> {
    public AjaxRes<ShoppingCart> add (ShoppingCart shoppingCart, HttpServletRequest request);
//    public AjaxRes<Page<ShoppingCart>> update();
    public AjaxRes<ShoppingCart> updateShoppingCart(ShoppingCart shoppingCart);
    public AjaxRes<String> clean ();
    public AjaxRes<Page<ShoppingCart>> getPostByPage(ShoppingCartParam param);

    public AjaxRes<Page<ShoppingCart>> getPostByUserId(ShoppingCartByIdParam param);
    public AjaxRes<Page<ShoppingCart>> getAllShoppingCart(Page page);
    public AjaxRes<String> deleteOneA(ShoppingCart shoppingCart,HttpServletRequest request);
}
