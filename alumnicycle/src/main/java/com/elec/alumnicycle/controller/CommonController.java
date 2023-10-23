package com.elec.alumnicycle.controller;

import com.elec.alumnicycle.common.AjaxRes;
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
import java.util.UUID;

/**
 * 文件上传下载
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
    public AjaxRes<List<GlobalSearchVo>> search(@RequestParam String keyword){
        return commonService.search(keyword);
    }

    @PostMapping("/upload")
    @ApiOperation(value = "upload image")
    public AjaxRes<String> upload(MultipartFile file){  //参数名需和前端生成保持一致,file为临时文件

//        使用UUID重新生成文件名防止重复覆盖
        String original = file.getOriginalFilename();
        String suffix = original.substring(original.lastIndexOf("."));
        String fileName = UUID.randomUUID()+ suffix;

//        判断目录是否存在
        File dir = new File(basePath);
        if(!dir.exists()){
//            需要创建目录
            dir.mkdirs();
        }

        try {
//            临时文件转存
            file.transferTo(new File(basePath + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info(file.toString());
//        文件名称存入数据库
        return AjaxRes.success(fileName);

    }

    @GetMapping("/download")
    @ApiOperation(value = "download image")
    public void download(String name, HttpServletResponse response){

        try {
//            输入流，读取文件
            FileInputStream fileInputStream = new FileInputStream(new File(basePath + name));
//            输出流，写回浏览器展示图片
            ServletOutputStream outputStream = response.getOutputStream();

//            固定文件类型
            response.setContentType("image/jpeg");
            byte[] bytes = new byte[10240];
            int len;
//            读取文件，按字节
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
