package com.elec.alumnicycle.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.elec.alumnicycle.common.AjaxRes;
import com.elec.alumnicycle.entity.ShoppingCart;
import com.elec.alumnicycle.entity.params.ShoppingCartByIdParam;
import com.elec.alumnicycle.entity.params.ShoppingCartParam;
import com.elec.alumnicycle.service.ShoppingCartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/shoppingCart")
@Api(tags = "Shopping cart page")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @PostMapping("/page")
    @ApiOperation(value = "get posts according to page and category")
    public AjaxRes<Page<ShoppingCart>> getPostByPage(@RequestBody ShoppingCartParam param){
        return shoppingCartService.getPostByPage(param);
    }

    @PostMapping ("/getByUserId")
    @ApiOperation(value = "get shoppingCart by userIds")
    public AjaxRes<Page<ShoppingCart>> getPostByUserId(@RequestBody ShoppingCartByIdParam param){
        return shoppingCartService.getPostByUserId(param);
    }

    @PostMapping("/add")
    @ApiOperation(value = "add item to the shopping cart")
    public AjaxRes<ShoppingCart> add(@RequestBody ShoppingCart shoppingCart, HttpServletRequest request){
        return shoppingCartService.add(shoppingCart,request);
    }

    @PostMapping("/update")
    @ApiOperation(value = "update shoppingCart")
    public AjaxRes<ShoppingCart> updateShoppingCart(@RequestBody ShoppingCart shoppingCart){
        return shoppingCartService.updateShoppingCart(shoppingCart);
    }

    @GetMapping("/clean")
    @ApiOperation(value = "delete all items in the shopping cart")
    public AjaxRes<String> clean(){
        return shoppingCartService.clean();
    }

    @PostMapping("/deleteOne")
    @ApiOperation(value = "delete one goods")
    public AjaxRes<String> deleteOne(@RequestBody ShoppingCart shoppingCart,HttpServletRequest request){
        return shoppingCartService.deleteOneA(shoppingCart,request);
    }
}