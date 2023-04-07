package com.hln.association.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hln.association.entity.ActMember;
import com.hln.association.entity.ActMemberColumn;
import com.hln.association.mapper.ActMemberColumnMapper;
import com.hln.association.mapper.ActMemberMapper;
import com.hln.association.service.ActMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hln.commonuntils.ResultCode;
import com.hln.servicebase.exceptionhandler.PracticeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 职协-会员管理 服务实现类
 * </p>
 *
 * @author wcg
 * @since 2022-11-02
 */
@Service
public class ActMemberServiceImpl extends ServiceImpl<ActMemberMapper, ActMember> implements ActMemberService {

    @Autowired
    private ActMemberColumnMapper actMemberColumnMapper;


    @Override
    public Map<String, Object> getMemberPage(String account, Integer page, Integer size) {
        QueryWrapper<ActMember> wrapper = new QueryWrapper<>();
        if(StringUtils.isNotEmpty(account)){
            wrapper.like("account",account);
        }
        wrapper.orderByDesc("gmt_create");
        IPage<ActMember> iPage = new Page<>(page,size);
        this.page(iPage,wrapper);
        List<ActMember> list = iPage.getRecords();
        long total = iPage.getTotal();
        Map<String,Object> item= new HashMap<>();
        item.put("total",total);
        item.put("list",list);
        return item;
    }

    @Override
    public void removeMember(Integer id) {
        //首先删除对应的字段显示内容
        QueryWrapper<ActMemberColumn> wrapper = new QueryWrapper<>();
        wrapper.eq("m_id",id);
        actMemberColumnMapper.delete(wrapper);
        //然后删除对应的数据
        this.removeById(id);
    }

    @Override
    public void addMember(List<ActMemberColumn> members) {
        //新增会员信息
        ActMember member = new ActMember();
        for (ActMemberColumn ac:members){
            if("account".equals(ac.getName())){
                member.setAccount(ac.getValue());
            }
            if("password".equals(ac.getName())){
                member.setPassword(ac.getValue());
            }
            if("unit".equals(ac.getName())){
                member.setUnit(ac.getValue());
            }
        }
        QueryWrapper<ActMember> wrapper = new QueryWrapper<>();
        wrapper.eq("account",member.getAccount());
        ActMember thisMember = this.getOne(wrapper);
        if(thisMember!=null){
            throw new PracticeException(ResultCode.ERROR,"账号已经存在！");
        }
        this.save(member);
        actMemberColumnMapper.addMemberColumnInfo(members,member.getId());
    }

    @Override
    public List<ActMemberColumn> getColumnInfo(Integer id) {
        QueryWrapper<ActMemberColumn> wrapper = new QueryWrapper<>();
        if(null==id){
            wrapper.isNull("m_id");
        }else {
            wrapper.eq("m_id",id);
        }
        wrapper.orderByAsc("gmt_create");
        return actMemberColumnMapper.selectList(wrapper);

    }

    @Override
    public void updateMember(List<ActMemberColumn> members) {
        //新增会员信息
        ActMember member = new ActMember();
        for (ActMemberColumn ac:members){
            if("account".equals(ac.getName())){
                member.setAccount(ac.getValue());
            }
            if("password".equals(ac.getName())){
                member.setPassword(ac.getValue());
            }
            if("unit".equals(ac.getName())){
                member.setUnit(ac.getValue());
            }
            member.setId(ac.getMId());
        }
        //账号不能重读
        QueryWrapper<ActMember> wrapper = new QueryWrapper<>();
        wrapper.eq("account",member.getAccount())
                .ne("id",member.getId());
        ActMember thisMember = this.getOne(wrapper);
        if(thisMember!=null){
            throw new PracticeException(ResultCode.ERROR,"账号已经存在！");
        }
        this.updateById(member);
        actMemberColumnMapper.updateMemberColumnInfo(members);

    }

    @Override
    public List<ActMemberColumn> getMemberInfo(String memberId) {
        QueryWrapper<ActMemberColumn> wrapper =  new QueryWrapper<>();
        wrapper.eq("m_id",memberId)
                .eq("status","N");
        return actMemberColumnMapper.selectList(wrapper);
    }


}
