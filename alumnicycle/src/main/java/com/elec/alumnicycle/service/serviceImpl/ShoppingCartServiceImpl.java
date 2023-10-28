package com.elec.alumnicycle.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.elec.alumnicycle.common.AjaxRes;
import com.elec.alumnicycle.common.BaseContext;
import com.elec.alumnicycle.entity.ShoppingCart;
import com.elec.alumnicycle.entity.params.ShoppingCartByIdParam;
import com.elec.alumnicycle.entity.params.ShoppingCartParam;
import com.elec.alumnicycle.mapper.ShoppingCartMapper;
import com.elec.alumnicycle.service.CreateShoppingCartService;
import com.elec.alumnicycle.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {
    @Autowired
    CreateShoppingCartService createShoppingCartService;
    @Override
    public AjaxRes<Page<ShoppingCart>> getPostByPage(ShoppingCartParam param) {
        LambdaQueryWrapper<ShoppingCart> lqw = new LambdaQueryWrapper<>();
        lqw.eq(param.getUnitPrice() != 0,ShoppingCart::getUnitPrice,param.getUnitPrice())
                .orderByDesc(ShoppingCart::getCreateTime);
        Page<ShoppingCart> pageInfo = new Page<>(param.getPage(), param.getPageSize());
        this.page(pageInfo,lqw);
        return AjaxRes.success(pageInfo);
    }
    @Override
    public AjaxRes<Page<ShoppingCart>> getPostByUserId(ShoppingCartByIdParam param) {
        return AjaxRes.success(this.getBaseMapper().getAllShoppingCartById(param.getPage(), param.getUserId()));
    }

    @Override
    public AjaxRes<Page<ShoppingCart>> getAllShoppingCart(Page page) {
        return null;
    }

    @Override
    public AjaxRes<ShoppingCart> add(ShoppingCart shoppingCart, HttpServletRequest request) {

        Long currentUserId = (Long)request.getSession().getAttribute("User");
        shoppingCart.setUserId(currentUserId);

        Long goodsId = shoppingCart.getGoodsId();

        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId, currentUserId);

        if (goodsId != null) {

            queryWrapper.eq(ShoppingCart::getGoodsId, goodsId);

        }

        //SQL:select * from shopping_cart where user_id = ? and dish_id/setmeal_id = ?
        ShoppingCart cartServiceOne = this.getOne(queryWrapper);

        if (cartServiceOne != null) {

            Integer number = cartServiceOne.getNumber();
            cartServiceOne.setNumber(number + 1);
            this.updateById(cartServiceOne);
        } else {

            shoppingCart.setNumber(1);
            shoppingCart.setCreateTime(LocalDateTime.now());
            this.save(shoppingCart);
            cartServiceOne = shoppingCart;
        }

        return AjaxRes.success(cartServiceOne);
    }


    @Override
    public AjaxRes<ShoppingCart> updateShoppingCart(ShoppingCart shoppingCart) {
        shoppingCart.setCreateTime(LocalDateTime.now());

        this.updateById(shoppingCart);

        return AjaxRes.success(shoppingCart);
    }
    @Override
    public AjaxRes<String> clean() {
        //SQL:delete from shopping_cart where user_id = ?

        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId, BaseContext.getCurrentId());

        this.remove(queryWrapper);

        return AjaxRes.success("success clean");
    }

    @Override
    public AjaxRes<String> deleteOneA(ShoppingCart shoppingCart,HttpServletRequest request) {

        Long currentUserId = (Long)request.getSession().getAttribute("User");
        shoppingCart.setUserId(currentUserId);
        Long goodsId = shoppingCart.getGoodsId();
        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId, currentUserId);
        if (goodsId != null) {

            queryWrapper.eq(ShoppingCart::getGoodsId, goodsId);

        }

        //SQL:select * from shopping_cart where user_id = ? and dish_id/setmeal_id = ?
        ShoppingCart cartServiceOne = this.getOne(queryWrapper);
        if (cartServiceOne != null) {
            if (cartServiceOne.getNumber() == 1){
                this.removeById(cartServiceOne.getId());
            } else if (cartServiceOne.getNumber() > 1){

                Integer number = cartServiceOne.getNumber();
                cartServiceOne.setNumber(number - 1);
                this.updateById(cartServiceOne);
            }
        }
        return AjaxRes.success("success delete");
    }


}