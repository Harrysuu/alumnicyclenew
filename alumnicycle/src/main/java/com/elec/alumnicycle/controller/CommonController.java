package com.elec.alumnicycle.controller;

import com.elec.alumnicycle.common.AjaxRes;
import com.elec.alumnicycle.entity.params.SearchRequest;
import com.elec.alumnicycle.entity.vo.GlobalSearchVo;
import com.elec.alumnicycle.service.CommonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * file upload and download
 */
@RestController
@RequestMapping("/common")
@Slf4j
@RequiredArgsConstructor
@Api(tags = "common API")
public class CommonController {

    @Autowired
    private CommonService commonService;

    @Value("${alumniCycle.path}")
    private String basePath;

    @PostMapping("/globalSearch")
    @ApiOperation(value = "Global Search")
    public AjaxRes<List<GlobalSearchVo>> search(@RequestBody SearchRequest searchRequest) {
        String keyword = searchRequest.getKeyword();
        return commonService.search(keyword);
    }

    @PostMapping("/upload")
    @ApiOperation(value = "upload image")
    public AjaxRes<String> upload(MultipartFile file){

        //Use UUID to regenerate file names to prevent duplicate overwriting
        String original = file.getOriginalFilename();
        String suffix = original.substring(original.lastIndexOf("."));
        String fileName = UUID.randomUUID()+ suffix;

        //Determine whether the directory exists
        File dir = new File(basePath);
        if(!dir.exists()){

            dir.mkdirs();
        }

        try {
            //Temporary file transfer
            file.transferTo(new File(basePath + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info(file.toString());

        return AjaxRes.success(fileName);

    }

    @GetMapping("/download")
    @ApiOperation(value = "download image")
    public void download(String name, HttpServletResponse response){

        try {
            // Input stream, read file
            FileInputStream fileInputStream = new FileInputStream(new File(basePath + name));
            // Output stream, write back to the browser to display the image
            ServletOutputStream outputStream = response.getOutputStream();

            // Fix file types
            response.setContentType("image/jpeg");
            byte[] bytes = new byte[10240];
            int len;
            // Read file, bytes
            while ((len = fileInputStream.read(bytes)) != -1){
                outputStream.write(bytes,0,len);
                outputStream.flush();
            }
            outputStream.close();
            fileInputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
