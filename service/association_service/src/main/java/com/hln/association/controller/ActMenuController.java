package com.hln.association.controller;


import com.hln.association.entity.ActMenu;
import com.hln.association.service.ActMenuService;
import com.hln.commonuntils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 职协-栏目管理 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-10-24
 */
@RestController
@RequestMapping("association/menu")
public class ActMenuController {

    @Autowired
    private ActMenuService actMenuService;

    /**
     * 获取菜单下拉列表
     */
    @GetMapping("getNewType/{type}")
    public R moveNew (@PathVariable Integer type){
        List<ActMenu> list =  actMenuService.getNewType(type);
        return R.ok().data("list",list);
    }


    /**
     * 分页列表
     */
    @GetMapping("getMenuPage")
    public R getMemberPage (String name,Integer page,Integer size){
        Map<String,Object> item = actMenuService.getMenuPage(name,page,size);
        return  R.ok().data("item",item);
    }

    /**
     * 删除
     */
    @GetMapping("remove/{id}")
    public R getMemberPage (@PathVariable Integer id ){
       actMenuService.removeById(id);
        return  R.ok();
    }

    /**
     * 新增
     */
    @PostMapping("addMenu")
    public R addMenu (ActMenu actMenu ){
        actMenuService.save(actMenu);
        return  R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("updateMenu")
    public R updateMenu (ActMenu actMenu ){
        actMenuService.updateById(actMenu);
        return  R.ok();
    }


}

