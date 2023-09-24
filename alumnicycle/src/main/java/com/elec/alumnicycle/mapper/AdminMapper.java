package com.elec.alumnicycle.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.elec.alumnicycle.entity.Admin;
import com.elec.alumnicycle.entity.User;
import org.mapstruct.Mapper;

@Mapper
public interface AdminMapper extends BaseMapper<Admin> {
}
