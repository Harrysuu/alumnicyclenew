package com.elec.alumnicycle.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.elec.alumnicycle.entity.Administrator;
import com.elec.alumnicycle.entity.User;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

@Mapper
public interface AdminMapper extends BaseMapper<Administrator> {

    Page<Administrator> getAllAdmin(@Param("page") Page page);

    Page<User> getAllUser(@Param("page") Page page);
}
