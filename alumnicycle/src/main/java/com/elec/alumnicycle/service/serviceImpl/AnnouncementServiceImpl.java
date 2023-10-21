package com.elec.alumnicycle.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.elec.alumnicycle.common.AjaxRes;
import com.elec.alumnicycle.common.BaseContext;
import com.elec.alumnicycle.entity.Announcement;
import com.elec.alumnicycle.entity.CreateAnnounce;
import com.elec.alumnicycle.entity.params.AnnouncementByIdParam;
import com.elec.alumnicycle.entity.params.AnnouncementParam;
import com.elec.alumnicycle.mapper.AnnouncementMapper;
import com.elec.alumnicycle.service.AnnouncementService;
import com.elec.alumnicycle.service.CreateAnnounceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class AnnouncementServiceImpl extends ServiceImpl<AnnouncementMapper, Announcement> implements AnnouncementService {

    @Autowired
    CreateAnnounceService createAnnounceService;

    @Override
    public AjaxRes<Page<Announcement>> getAnnouncementByPage(AnnouncementParam param) {
        LambdaQueryWrapper<Announcement> lqw = new LambdaQueryWrapper<>();
        lqw.orderByDesc(Announcement::getPostTime);
        Page<Announcement> pageInfo = new Page<>(param.getPage(), param.getPageSize());
        this.page(pageInfo,lqw);
        return AjaxRes.success(pageInfo);
    }

    @Override
    public AjaxRes<String> addAnnouncement(HttpServletRequest request, Announcement announcement) {
        //get admin id
//        Long adminId = BaseContext.getCurrentId();
//        adminId = 99L;

        //get admin id
        Long adminId = (Long) request.getSession().getAttribute("Admin");

        //set post time and Id
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
    public AjaxRes<Page<Announcement>> getPostByAdminId(AnnouncementByIdParam param) {
        Page<Announcement> announcements = this.getBaseMapper().getAnnouncementByAdminId(param.getPage(),param.getAdminId());
        return AjaxRes.success(announcements);
    }

    @Override
    public AjaxRes<List<Announcement>> getStared() {
        LambdaQueryWrapper<Announcement> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Announcement::getStar, 1);
        lqw.orderByDesc(Announcement::getPostTime);

        List<Announcement> stared = this.list(lqw);

        if(stared.size() >3){
            stared = stared.subList(0,3);
        }
        return AjaxRes.success(stared);
    }

    @Override
    public AjaxRes<Announcement> getAnnouncementById(Long id) {
        LambdaQueryWrapper<Announcement> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Announcement::getId, id);

        Announcement announcement = this.getOne(lqw);
        return AjaxRes.success(announcement);
    }

}
