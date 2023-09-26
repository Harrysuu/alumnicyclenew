package com.elec.alumnicycle.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.elec.alumnicycle.common.AjaxRes;
import com.elec.alumnicycle.common.BaseContext;
import com.elec.alumnicycle.entity.Admin;
import com.elec.alumnicycle.entity.Announcement;
import com.elec.alumnicycle.entity.CreateAnnounce;
import com.elec.alumnicycle.entity.LifePost;
import com.elec.alumnicycle.entity.params.AnnouncementByIdParam;
import com.elec.alumnicycle.entity.params.AnnouncementParam;
import com.elec.alumnicycle.mapper.AdminMapper;
import com.elec.alumnicycle.mapper.AnnouncementMapper;
import com.elec.alumnicycle.service.AdminService;
import com.elec.alumnicycle.service.AnnouncementService;
import com.elec.alumnicycle.service.CreateAnnounceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class AnnouncementServiceImpl extends ServiceImpl<AnnouncementMapper, Announcement> implements AnnouncementService {

    @Autowired
    CreateAnnounceService createAnnounceService;

    @Autowired
    AnnouncementMapper announcementMapper;

    @Override
    public AjaxRes<Page<Announcement>> getAnnouncementByPage(AnnouncementParam param) {
        LambdaQueryWrapper<Announcement> lqw = new LambdaQueryWrapper<>();
        lqw.orderByDesc(Announcement::getPostTime);
        Page<Announcement> pageInfo = new Page<>(param.getPage(), param.getPageSize());
        this.page(pageInfo,lqw);
        return AjaxRes.success(pageInfo);
    }

    @Override
    public AjaxRes<String> addAnnouncement(Announcement announcement) {
        //get admin id
        Long adminId = BaseContext.getCurrentId();

        // test only
        adminId = 99L;

        //set post time and Id
        long id = IdWorker.getId();
        announcement.setId(id);
        announcement.setPostTime(LocalDateTime.now());

        // save to announcement
        this.save(announcement);
        // star = 1: stared announcement; star = 0: normal announcement
        log.info(String.valueOf(announcement.getStar()));

        //save to link table CreateAnnounce
        CreateAnnounce create = new CreateAnnounce();
        create.setAnnouncementId(announcement.getId());
        create.setAdminId(adminId);
        createAnnounceService.save(create);

        return AjaxRes.success("New announcement is added");

    }

    @Override
    public AjaxRes<String> deleteAnnouncement(Long id) {
        //delete link table CreateAnnounce
        LambdaQueryWrapper<CreateAnnounce> lqw = new LambdaQueryWrapper<>();
        lqw.eq(CreateAnnounce::getAnnouncementId, id);
        createAnnounceService.remove(lqw);

        // delete post by id
        this.removeById(id);
        return AjaxRes.success("The Announcement is deleted");

    }

    @Override
    public AjaxRes<Announcement> updateAnnouncement(Announcement announcement) {
        announcement.setPostTime(LocalDateTime.now());

        this.updateById(announcement);
        return AjaxRes.success(announcement);
    }

    @Override
    public AjaxRes<Page<Announcement>> getPostbyAdminId(AnnouncementByIdParam param) {
      List<Announcement> announcements = announcementMapper.getAnnouncementByAdminId(param.getAdminId());

      Page<Announcement> page = new Page<>(param.getPage(), param.getPageSize());
      page.setRecords(announcements);

      return AjaxRes.success(page);
    }

    @Override
    public AjaxRes<List<Announcement>> getStared() {
        List<Announcement> stared = new ArrayList<>();
        LambdaQueryWrapper<Announcement> lqw = new LambdaQueryWrapper();
        lqw.eq(Announcement::getStar, 1);
        lqw.orderByDesc(Announcement::getPostTime);

        stared = this.list(lqw);

        return AjaxRes.success(stared);
    }


}
