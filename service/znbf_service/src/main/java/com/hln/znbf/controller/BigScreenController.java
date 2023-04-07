package com.hln.znbf.controller;

import com.hln.commonuntils.R;
import com.hln.znbf.entity.Dsrnh;
import com.hln.znbf.entity.Statistics;
import com.hln.znbf.service.BigScreenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 浙农帮扶大屏接口
 */
@RequestMapping("/znbf/bigscreen")
@RestController
public class BigScreenController {

    @Resource
    private BigScreenService bigScreenService;


    /**
     * 获取政策，心愿，就业，各个条数；左上
     * @param areaCode
     * @return
     */
    @GetMapping("getFourData")
    public R getFourData(String areaCode){
        Map<String,Object>  item = bigScreenService.getFourData(areaCode);
        return R.ok().data("item",item);
    }

    /**
     * 需求解决中
     */
    @GetMapping("getDoingInfo")
    public R getDoingInfo(String areaCode){
        Map<String,Object>   item = bigScreenService.getDoingInfo(areaCode);
        return R.ok().data("item",item);
    }

    /**
     * 获取上方指标
     * 农户数，已解决，解决中
     */
    @GetMapping("getThreeIndex")
    public R getThreeIndex(String areaCode){
        Map<String,Object>   item = bigScreenService.getThreeIndex(areaCode);
        return R.ok().data("item",item);
    }

    /**
     * 获取地图数据
     */
    @GetMapping("getMapData")
    public R getMapData(String areaCode){
        List<Map<String,Object>>   item = bigScreenService.getMapData(areaCode);
        return R.ok().data("item",item);
    }

    /**
     * 右上各个地区的需求数
     * @param areaCode
     * @return
     */
    @GetMapping("getAreaList")
    public R getAreaList(String areaCode){
        List<Map<String,Object>> item = bigScreenService.getAreaList(areaCode);
        return R.ok().data("item",item);
    }
}
