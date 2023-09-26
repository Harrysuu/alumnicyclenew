package com.elec.alumnicycle.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.elec.alumnicycle.entity.Announcement;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AnnouncementMapper extends BaseMapper<Announcement> {

    List<Announcement> getAnnouncementByAdminId(@Param("adminId") Long adminId);
}
