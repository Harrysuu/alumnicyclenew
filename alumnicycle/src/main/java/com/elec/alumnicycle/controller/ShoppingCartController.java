package com.elec.alumnicycle.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.elec.alumnicycle.common.AjaxRes;
import com.elec.alumnicycle.common.BaseContext;
import com.elec.alumnicycle.entity.ShoppingCart;
import com.elec.alumnicycle.service.ShoppingCartService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 购物车
 */
@Slf4j
@RestController
@RequestMapping("/shoppingCart")
@Api(tags = "Shopping cart page")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    /**
     * 添加购物车
     * @param shoppingCart
     * @return
     */
    @PostMapping("/add")
    public AjaxRes<ShoppingCart> add(@RequestBody ShoppingCart shoppingCart){
        return shoppingCartService.add(shoppingCart);
    }

    /**
     * 查看购物车
     * @return
     */
    @GetMapping("/list")
    public AjaxRes<Page<ShoppingCart>> page(){
        return shoppingCartService.page();
    }
    /**
     * 清空购物车
     * @return
     */
    @GetMapping("/clean")
    public AjaxRes<String> clean(){
        return shoppingCartService.clean();
    }
}