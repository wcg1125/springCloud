package com.hln.znbf.controller;


import com.hln.commonuntils.R;
import com.hln.znbf.entity.Policy;
import com.hln.znbf.service.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 * 浙农帮扶——政策 前端控制器
 * </p>
 *
 * @author wcg
 * @since 2022-12-01
 */
@RestController
@RequestMapping("/znbf/policy")
public class PolicyController {

    @Autowired
    private PolicyService policyService;

    /**
     * 我的政策
     */

    @GetMapping("getMyPolicyList")
    public R getMyPolicyList(HttpServletRequest request,String title,String type,String category){
        Map<String,Object> item = policyService.getMyPolicyList(request,title,type,category);
        return  R.ok().data("item",item);
    }

    /**
     * 详情
     */
    @GetMapping("getMyPolicyById")
    public R getMyPolicyById(String id){
        Policy item = policyService.getMyPolicyById(id);
        return  R.ok().data("item",item);
    }


    /**
     * 后台分页列表
     */
    @GetMapping("getPolicyList")
    public R getPolicyList(String areaCode,String unit,String title,Integer pageNum,Integer pageSize){
        Map<String,Object> item = policyService.getPolicyList(areaCode,unit,title,pageNum,pageSize);
        return  R.ok().data("item",item);
    }

    /**
     * add
     */
    @PostMapping("savePolicy")
    public R savePolicy(Policy policy){
        policyService.save(policy);
        return  R.ok();
    }
    /**
     * update
     */
    @PostMapping("updatePolicy")
    public R updatePolicy(Policy policy){
        policyService.updateById(policy);
        return  R.ok();
    }
}

