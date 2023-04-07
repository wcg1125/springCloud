package com.hln.association.controller;


import com.hln.association.entity.ActMemberColumn;
import com.hln.association.service.ActMemberService;
import com.hln.commonuntils.JwtUtils;
import com.hln.commonuntils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 职协-会员管理 前端控制器
 * </p>
 *
 * @author wcg
 * @since 2022-11-02
 */
@RestController
@RequestMapping("association/member")
public class ActMemberController {

    @Autowired
    private ActMemberService actMemberService;

    /**
     * 分页列表
     * @param account
     * @param page
     * @param size
     * @return
     */
    @GetMapping("getMemberPage")
    public R getMemberPage (String account,Integer page,Integer size){
        Map<String,Object> item = actMemberService.getMemberPage(account,page,size);
        return  R.ok().data("item",item);
    }

    /**
     * 删除
     */
    @GetMapping("remove/{id}")
    public R removeMember (@PathVariable Integer id ){
        actMemberService.removeMember(id);
        return  R.ok();
    }

    /**
     * 获取字段信息n
     */
    @GetMapping("getColumnInfo")
    public R getColumnInfo ( Integer id ){
        List<ActMemberColumn> list =  actMemberService.getColumnInfo(id);
        return  R.ok().data("list",list);
    }

    /**
     * 新增
     */
    @PostMapping("addMember")
    public R addMember (@RequestBody List<ActMemberColumn> members){
       actMemberService.addMember(members);
        return  R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("updateMember")
    public R updateMember (@RequestBody List<ActMemberColumn> members){
        actMemberService.updateMember(members);
        return  R.ok();
    }


    /**
     * 前台会员中心数据展示
     */
    @GetMapping("getMemberInfo")
    public R getMemberInfo (HttpServletRequest request){
        //调用jwt工具类的方法。根据request对象获取头信息，返回用户id
        String memberId = JwtUtils.getIdByJwtToken(request);
        actMemberService.getMemberInfo(memberId);
        return  R.ok();
    }
}

