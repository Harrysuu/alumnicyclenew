package com.elec.alumnicycle.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.elec.alumnicycle.entity.Enrol;
import com.elec.alumnicycle.mapper.EnrolMapper;
import com.elec.alumnicycle.service.EnrolService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EnrolServiceImpl extends ServiceImpl<EnrolMapper, Enrol> implements EnrolService {
}
