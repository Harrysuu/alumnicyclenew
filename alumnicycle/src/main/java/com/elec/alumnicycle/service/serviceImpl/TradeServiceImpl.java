package com.elec.alumnicycle.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.elec.alumnicycle.common.AjaxRes;
import com.elec.alumnicycle.entity.SecondPost;
import com.elec.alumnicycle.entity.ShoppingCart;
import com.elec.alumnicycle.entity.Trade;
import com.elec.alumnicycle.entity.User;
import com.elec.alumnicycle.entity.params.TradeParam;
import com.elec.alumnicycle.mapper.TradeMapper;
import com.elec.alumnicycle.service.SecondPostService;
import com.elec.alumnicycle.service.ShoppingCartService;
import com.elec.alumnicycle.service.TradeService;
import com.elec.alumnicycle.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class TradeServiceImpl extends ServiceImpl<TradeMapper, Trade>implements TradeService {
    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private SecondPostService secondPostService;

    @Autowired
    private UserService userService;

    @Override
    public AjaxRes<Page<Trade>> getTradeByPage(TradeParam param) {
        // get page by lwq
        LambdaQueryWrapper<Trade> lwq = new LambdaQueryWrapper<>();
        lwq.eq(Trade::getCount, param.getCount());
        lwq.orderByDesc(Trade::getGenerationTime);

        // set to pageInfo
        Page<Trade> pageInfo = new Page<>(param.getPage(), param.getPageSize());
        this.page(pageInfo, lwq);
        return  AjaxRes.success(pageInfo);
    }

    @Override
    public AjaxRes<Trade> getTradeById(Long tradeId) {
        // get Order
        LambdaQueryWrapper<Trade> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Trade::getId, tradeId);
        Trade trade = this.getOne(lqw);

        // return Order
        return AjaxRes.success(trade);
    }

    @Override
    @Transactional
    public AjaxRes<String> submit(HttpServletRequest request) {

        Long buyerId = (Long)request.getSession().getAttribute("User");
        LambdaQueryWrapper<ShoppingCart> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ShoppingCart::getUserId,buyerId);
        List<ShoppingCart> shoppingCarts = shoppingCartService.list(wrapper);

        if (shoppingCarts == null || shoppingCarts.isEmpty()) {
            AjaxRes.failMsg("cart is empty");
        }

        double totalAmount = 0.0;
        for (ShoppingCart cart : shoppingCarts) {
            double itemTotal = cart.getUnitPrice() * cart.getNumber();
            totalAmount += itemTotal;
        }

        User buyer = userService.getById(buyerId);

        if (buyer.getCredit() < totalAmount) {
            return AjaxRes.failMsg("credit is not enough to pay");
        }

        buyer.setCredit(buyer.getCredit() - totalAmount);
        userService.updateById(buyer);

        for (ShoppingCart shoppingCart : shoppingCarts) {
            Trade tradeNew = new Trade();
            SecondPost secondPost = secondPostService.getById(shoppingCart.getGoodsId());
            tradeNew.setSellerId(secondPost.getPosterId());
            tradeNew.setBuyerId(shoppingCart.getUserId());
            tradeNew.setCommodityId(shoppingCart.getGoodsId());
            tradeNew.setCommodityName(secondPost.getCommodityName());
            tradeNew.setCount(shoppingCart.getNumber());
            tradeNew.setStatus(2);
            tradeNew.setUnitPrice(shoppingCart.getUnitPrice());
            tradeNew.setTotalPrice(shoppingCart.getUnitPrice()*shoppingCart.getNumber());
            tradeNew.setGenerationTime(LocalDateTime.now());
            this.save(tradeNew);
        }

        shoppingCartService.remove(wrapper);
        return AjaxRes.success("submit success");
    }


}
