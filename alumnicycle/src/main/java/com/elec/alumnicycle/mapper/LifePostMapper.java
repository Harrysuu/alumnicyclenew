package com.elec.alumnicycle.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.elec.alumnicycle.entity.LifePost;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface LifePostMapper extends BaseMapper<LifePost> {

    Page<LifePost> getLifePostsByUserId(@Param("page") Page page, @Param("userId") Long userId);
}
