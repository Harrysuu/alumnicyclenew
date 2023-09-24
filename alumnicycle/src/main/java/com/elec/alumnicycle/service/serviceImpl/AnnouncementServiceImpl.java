package com.elec.alumnicycle.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.elec.alumnicycle.entity.Admin;
import com.elec.alumnicycle.entity.Announcement;
import com.elec.alumnicycle.mapper.AdminMapper;
import com.elec.alumnicycle.mapper.AnnouncementMapper;
import com.elec.alumnicycle.service.AdminService;
import com.elec.alumnicycle.service.AnnouncementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AnnouncementServiceImpl extends ServiceImpl<AnnouncementMapper, Announcement> implements AnnouncementService {
}
