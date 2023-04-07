package com.hln.association.controller;


import com.hln.association.entity.ActFile;
import com.hln.association.service.ActFileService;
import com.hln.commonuntils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wcg
 * @since 2022-11-07
 */
@RestController
@RequestMapping("/association/file")
public class ActFileController {


    @Autowired
    private ActFileService actFileService;

    /**
     * 保存
     */
    @PostMapping("saveActFile")
    public R saveActFile(@RequestBody List<ActFile> actFiles,String type) {
        actFileService.saveActFile(actFiles,type);
        return R.ok();
    }
}

