package com.elec.alumnicycle.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.elec.alumnicycle.common.AjaxRes;
import com.elec.alumnicycle.entity.Announcement;
import com.elec.alumnicycle.entity.LifePost;
import com.elec.alumnicycle.entity.params.AnnouncementByIdParam;
import com.elec.alumnicycle.entity.params.AnnouncementParam;
import com.elec.alumnicycle.entity.params.LifePostByIdParam;
import com.elec.alumnicycle.service.AnnouncementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/announcement")
@Slf4j
@Api(tags = "announcement pages")
public class AnnouncementController {

    @Autowired
    private AnnouncementService announcementService;

    @PostMapping("/page")
    @ApiOperation(value = "get announcements according to page")
    public AjaxRes<Page<Announcement>> getAnnouncementByPage(@RequestBody AnnouncementParam param){
        return announcementService.getAnnouncementByPage(param);
    }

    @PostMapping("/add")
    @ApiOperation(value = "add a new announcement")
    public AjaxRes<String> addAnnouncement(@RequestBody Announcement announcement){
        return announcementService.addAnnouncement(announcement);
    }

    @GetMapping("/delete")
    @ApiOperation(value = "delete announcement")
    public AjaxRes<String> deleteAnnouncement(Long id){
        return announcementService.deleteAnnouncement(id);
    }

    @PostMapping("/update")
    @ApiOperation(value = "update announcement")
    public AjaxRes<Announcement> updateAnnouncement(@RequestBody Announcement announcement){
        return announcementService.updateAnnouncement(announcement);
    }

    @PostMapping("/getByAdminId")
    @ApiOperation(value = "get announcement by adminId")
    public AjaxRes<Page<Announcement>> getPostByUserId(@RequestBody AnnouncementByIdParam param){
        return announcementService.getPostByAdminId(param);
    }

    @GetMapping("/getStared")
    @ApiOperation(value = "get all stared announcements")
    public AjaxRes<List<Announcement>> getStared(){
        return announcementService.getStared();
    }

    @GetMapping("/getAnnouncementById")
    @ApiOperation(value = "get by announcementID")
    public AjaxRes<Announcement> getAnnouncementById(Long id){
        return announcementService.getAnnouncementById(id);
    }

}
