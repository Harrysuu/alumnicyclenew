package com.elec.alumnicycle.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.elec.alumnicycle.common.AjaxRes;
import com.elec.alumnicycle.entity.Announcement;
import com.elec.alumnicycle.entity.params.AnnouncementByIdParam;
import com.elec.alumnicycle.entity.params.AnnouncementParam;

import java.util.List;

public interface AnnouncementService extends IService<Announcement> {
    AjaxRes<Page<Announcement>> getAnnouncementByPage(AnnouncementParam param);

    AjaxRes<String> addAnnouncement(Announcement announcement);

    AjaxRes<String> deleteAnnouncement(Long id);

    AjaxRes<Announcement> updateAnnouncement(Announcement announcement);

    AjaxRes<Page<Announcement>> getPostbyAdminId(AnnouncementByIdParam param);

    AjaxRes<List<Announcement>> getStared();
}
