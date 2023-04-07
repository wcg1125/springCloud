package com.hln.association.controller;


import com.hln.association.entity.ActLog;
import com.hln.association.service.ActLogService;
import com.hln.commonuntils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wcg
 * @since 2022-11-08
 */
@RestController
@RequestMapping("/association/log")
public class ActLogController {

    @Autowired
    private ActLogService actLogService;

    /**
     * 折现图
     * @param time
     * @return
     */
    @GetMapping("getActLogData")
    public R getActLogList(String time){
        Map<String,Object> item =   actLogService.getActLogList(time);
        return R.ok().data("item",item);
    }

}

