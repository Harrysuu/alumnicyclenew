package com.elec.alumnicycle.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.elec.alumnicycle.entity.CreateSecondPost;
import com.elec.alumnicycle.entity.CreateShoppingCart;
import com.elec.alumnicycle.mapper.CreateSecondPostMapper;
import com.elec.alumnicycle.mapper.CreateShoppingCartMapper;
import com.elec.alumnicycle.service.CreateSecondPostService;
import com.elec.alumnicycle.service.CreateShoppingCartService;
import org.springframework.stereotype.Service;
@Service
public class CreateShoppingCartServiceImpl extends ServiceImpl<CreateShoppingCartMapper, CreateShoppingCart> implements CreateShoppingCartService {
}
