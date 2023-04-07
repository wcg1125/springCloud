package com.hln.znbf.controller;


import com.hln.commonuntils.R;
import com.hln.znbf.entity.Work;
import com.hln.znbf.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 * 浙农帮扶——岗位详情 前端控制器
 * </p>
 *
 * @author wcg
 * @since 2022-12-02
 */
@RestController
@RequestMapping("/znbf/work")
public class WorkController {

    @Autowired
    private WorkService workService;

    /**
     * 移动端岗位列表
     * @param request
     * @param name
     * @param pageSize
     * @param pageNum
     * @return
     */
    @GetMapping("getMyWorkList")
    public R getMyWorkList(HttpServletRequest request, String name,Integer pageSize,Integer pageNum){
        Map<String,Object> item = workService.getMyWorkList(request,name,pageNum,pageSize);
        return  R.ok().data("item",item);
    }

    /**.
     * 查看详情
     * @param workId
     * @return
     */
    @GetMapping("getWorkInfo/{workId}")
    public R getWorkInfo(@PathVariable Integer  workId){
        Work item = workService.getWorkInfo(workId);
        return  R.ok().data("item",item);
    }


    /**
     * 申请列表
     */
    @GetMapping("getMyApplyList")
    public R getMyApplyList(HttpServletRequest request, Integer pageSize,Integer pageNum){
        Map<String,Object> item = workService.getMyApplyList(request,pageNum,pageSize);
        return  R.ok().data("item",item);
    }

    /**
     * 一键投递
     * @param workId
     * @param request
     * @return
     */
    @PostMapping("applyWork/{workId}")
    public R applyWork(@PathVariable("workId") Integer  workId,HttpServletRequest request){
         workService.applyWork(workId,request);
        return  R.ok();
    }

    /**
     * 后台岗位分页页表
     */
    @GetMapping("getWorkList")
    public R getWorkList(String areaCode,String name,Integer pageSize,Integer pageNum){
        Map<String,Object> item = workService.getWorkList(areaCode,name,pageNum,pageSize);
        return  R.ok().data("item",item);
    }

    /**
     * 后台岗位审核列表
     */
    @GetMapping("getApplyList")
    public R getApplyList(String workName,String name,Integer pageSize,Integer pageNum,String status){
        Map<String,Object> item = workService.getApplyList(workName,name,pageNum,pageSize,status);
        return  R.ok().data("item",item);
    }

    /**
     * 新增岗位
     */
    @PostMapping("saveWork")
    public R getWorkInfo(Work work){
         work.setStatus("N");
         workService.save(work);
        return  R.ok();
    }
    /**
     * 修改岗位
     */
    @PostMapping("updateWork")
    public R updateWork(Work work){
        workService.updateWork(work);
        return  R.ok();
    }

    /**
     * 删除岗位
     */
    
    @GetMapping("removeWork/{workId}")
    public R updateWork(@PathVariable Integer  workId){
        workService.removeById(workId);
        return  R.ok();
    }
}

