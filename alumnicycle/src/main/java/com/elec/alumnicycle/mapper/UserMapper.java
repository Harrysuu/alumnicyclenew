package com.elec.alumnicycle.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.elec.alumnicycle.entity.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper extends  BaseMapper<User> {
}

