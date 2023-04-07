package com.hln.association.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hln.association.entity.ActCertificate;
import com.hln.association.entity.excel.CretData;
import com.hln.association.listener.CretExcelListener;
import com.hln.association.mapper.ActCertificateMapper;
import com.hln.association.service.ActCertificateService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hln.commonuntils.ResultCode;
import com.hln.servicebase.exceptionhandler.PracticeException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.*;

/**
 * <p>
 * 职协-证书管理 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-10-25
 */
@Service
public class ActCertificateServiceImpl extends ServiceImpl<ActCertificateMapper, ActCertificate> implements ActCertificateService {

    /**
     * excel格式限制
     */
    private static Set<String> typeSet = new HashSet<>();
    static {
        typeSet.add(".xlsx");
        typeSet.add(".xls");
    }
    //添加课程分类


    @Override
    public Map<String, Object> getCretList(Integer page, Integer size, String code, String title) {
        if(page==null || size==null){
            throw new PracticeException(ResultCode.ERROR,"参数缺失！");
        }
        QueryWrapper<ActCertificate> wrapper = new QueryWrapper<>();
        if(StringUtils.isNotEmpty(code)){
            wrapper.like("code",code);
        }
        if(StringUtils.isNotEmpty(title)){
            wrapper.like("title",title);
        }
        wrapper.orderByDesc("gmt_create");
        IPage<ActCertificate> iPage = new Page<>(page,size);
         this.page(iPage, wrapper);
         Map<String,Object> item = new HashMap<>();
         item.put("total",iPage.getTotal());
         item.put("item",iPage.getRecords());
        return item;
    }

    @Override
    public Map<String, Object> importExcel(MultipartFile excelFile) {
        String fileName = excelFile.getOriginalFilename();
        //1.2 获取excel的类型
        int index = fileName.lastIndexOf(".");
        String fileType = fileName.substring(index);
        if(!typeSet.contains(fileType)){
            throw new PracticeException(ResultCode.ERROR,"文件类型不匹配！");
        }
        if(excelFile.isEmpty()){
            throw new PracticeException(ResultCode.ERROR,"文件内容为空！");
        }
        //成功条数
        Integer successTotal=0;
        //失败条数
        Integer failTotal=0;
        //失败的集合
        List<CretData> list = new ArrayList<>();
        Map<String,Object> item = new HashMap<>();
        item.put("successTotal",successTotal);
        item.put("failTotal",failTotal);
        item.put("failList",list);
        try {
            //获取文件流
            InputStream file = excelFile.getInputStream();
            EasyExcel.read(file, CretData.class,new CretExcelListener(this.baseMapper,item)).sheet().doRead();

        }catch (Exception e){
            e.printStackTrace();
        }

        return item;
    }

    @Override
    public ActCertificate selectCret(String code) {
        QueryWrapper<ActCertificate> wrapper = new QueryWrapper<>();
        wrapper.eq("code",code);
        return this.getOne(wrapper);
    }
}
