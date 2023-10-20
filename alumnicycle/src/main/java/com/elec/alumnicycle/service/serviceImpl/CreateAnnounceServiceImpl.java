package com.elec.alumnicycle.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.elec.alumnicycle.entity.CreateAnnounce;
import com.elec.alumnicycle.mapper.CreateAnnounceMapper;
import com.elec.alumnicycle.service.CreateAnnounceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CreateAnnounceServiceImpl extends ServiceImpl<CreateAnnounceMapper, CreateAnnounce> implements CreateAnnounceService {
}
