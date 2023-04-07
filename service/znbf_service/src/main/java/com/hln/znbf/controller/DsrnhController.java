package com.hln.znbf.controller;


import com.hln.commonuntils.R;
import com.hln.znbf.entity.Dsrnh;
import com.hln.znbf.entity.Region;
import com.hln.znbf.service.DsrnhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wcg
 * @since 2022-11-18
 */
@RestController
@RequestMapping("/znbf/dsrnh")
public class DsrnhController {


    @Autowired
    private DsrnhService dsrnhService;


    /**
     * 移动端获取个人信息
     * @param request
     * @return
     */
    @GetMapping("getMineInfo")
    public R getMineInfo(HttpServletRequest request){
        Dsrnh dsrnh = dsrnhService.getMineInfo(request);
        return R.ok().data("item",dsrnh);
    }

    /**
     * 查看详情
     * @param userId
     * @return
     */
    @GetMapping("getDsrnhInfo/{userId}")
    public R getDsrnhInfo(@PathVariable String userId){
        Dsrnh dsrnh = dsrnhService.getByuserId(userId);
        return R.ok().data("item",dsrnh);
    }

    /**
     * 分页列表
     */
    @GetMapping("getDsrnhList")
    public R getDsrnhList(String areaCode,String sfzh,String type,String name,Integer pageSize,Integer pageNum){
        Map<String,Object> dsrnhs = dsrnhService.getDsrnhList(areaCode,sfzh,type,name,pageNum,pageSize);
        return R.ok().data("item",dsrnhs);
    }

    /**
     * 地区下拉框
     */
    @GetMapping("getAreaList/{areaCode}")
    public R getAreaList(@PathVariable String areaCode){
        List<Region> areas = dsrnhService.getAreaList(areaCode);
        return R.ok().data("item",areas );
    }

}

