package com.elec.alumnicycle.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.elec.alumnicycle.entity.LifePost;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface LifePostMapper extends BaseMapper<LifePost> {
    List<LifePost> getLifePostsByUserId(@Param("userId") Long userId);

}
