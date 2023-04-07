package com.hln.association.controller;


import com.hln.association.entity.ActNews;
import com.hln.association.service.ActNewsService;
import com.hln.commonuntils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 职协-新闻管理 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-10-24
 */
@RestController
@RequestMapping("association/news")
public class ActNewsController {

    @Autowired
    private ActNewsService actNewsService;

    /**
     * 列表
     * @param page
     * @param size
     * @param type
     * @return
     */
    @GetMapping("getNewList")
    public R getNewList ( Integer page,  Integer size, String type){
        Map<String,Object> item = actNewsService.getNewList(page,size,type);
        return R.ok().data("item",item);
    }

    /**
     * 删除
     */
    @GetMapping("remove/{id}")
    public R getNewList (@PathVariable Integer id){
       actNewsService.removeById(id);
        return R.ok();
    }

    /**
     * 新增
     */

    @PostMapping("addNews")
    public R addNews (ActNews actNews){
        actNewsService.addNews(actNews);
        return R.ok();
    }

    /**
     * 修改
     */

    @PostMapping("updateNews")
    public R updateNews (ActNews actNews){
        actNewsService.updateById(actNews);
        return R.ok();
    }

    /**
     * 上移或者下移
     */
    @GetMapping("moveNew/{id}/{status}")
    public R moveNew (@PathVariable Integer id , @PathVariable String status){
        actNewsService.moveNew(id,status);
        return R.ok();
    }



}

