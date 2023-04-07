package com.hln.oss.controller;

import com.hln.commonuntils.R;
import com.hln.oss.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * oss存储服务
 */
@RestController
@RequestMapping("oss/img")
public class OssController {

    @Autowired
    private OssService ossService;
    /**
     * 职协图片服务
     */
    @GetMapping("uploadActFile")
    public R uploadActFile(MultipartFile file,String source){
        String url = ossService.uploadActFile(file,source);
        return R.ok().data("url",url);
    }

}
