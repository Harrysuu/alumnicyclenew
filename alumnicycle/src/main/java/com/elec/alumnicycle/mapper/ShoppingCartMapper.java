package com.elec.alumnicycle.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.elec.alumnicycle.entity.SecondPost;
import com.elec.alumnicycle.entity.ShoppingCart;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ShoppingCartMapper extends BaseMapper<ShoppingCart> {
    Page<SecondPost> getSecondPostsByUserId(@Param("page") Page page, @Param("userId") Long userId);

    Page<SecondPost> getAllSecondPost(@Param("page") Page page);

    Page<ShoppingCart> getAllShoppingCartById(@Param("page") Page page, @Param("userId") Long userId);
}
