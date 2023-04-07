package com.hln.association.controller;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.hln.association.entity.ActCertificate;
import com.hln.association.service.ActCertificateService;
import com.hln.commonuntils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * <p>
 * 职协-证书管理 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-10-25
 */
@RestController
@RequestMapping("/association/cert")
public class ActCertificateController {


    @Autowired
    private ActCertificateService actCertificateService;


    /**
     *
     * @param page
     * @param size
     * @param code 证书号
     * @param title 项目名称
     * @return
     */
    @GetMapping("getCretList")
    public R getCretList(Integer page,Integer size,String code,String title){
        Map<String,Object> item= actCertificateService.getCretList(page,size,code,title);
        return R.ok().data("item",item);
    }


    /**
     * 删除
     */
    @GetMapping("remove/{id}")
    public R getCretList(@PathVariable Integer id){
         actCertificateService.removeById(id);
        return R.ok();
    }

    /**
     * 新增
     */
    @PostMapping("addCret")
    public R addCret(ActCertificate actCertificate){
        actCertificateService.save(actCertificate);
        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("updateCret")
    public R updateCret(ActCertificate actCertificate){
        if(actCertificate.getId()==null){
            return R.error().message("参数缺失！");
        }
        actCertificateService.updateById(actCertificate);
        return R.ok();
    }

    /**
     * 批量导入
     */
    @GetMapping("import")
    public R importExcel(MultipartFile excelFile){
       Map<String,Object> item =  actCertificateService.importExcel(excelFile);
        return R.ok().data("item",item);
    }
}

