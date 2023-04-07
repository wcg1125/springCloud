package com.hln.association.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hln.association.entity.ActMember;
import com.hln.association.entity.ActMenu;
import com.hln.association.mapper.ActMenuMapper;
import com.hln.association.service.ActMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 职协-栏目管理 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-10-24
 */
@Service
public class ActMenuServiceImpl extends ServiceImpl<ActMenuMapper, ActMenu> implements ActMenuService {

    @Override
    public List<ActMenu> getNewType(Integer type) {
        QueryWrapper<ActMenu> wrapper = new QueryWrapper<>();
        wrapper.eq("type",type)
                .orderByDesc("gmt_create");
        List<ActMenu> list = this.baseMapper.selectList(wrapper);
         return list;
    }

    @Override
    public Map<String, Object> getMenuPage(String name, Integer page, Integer size) {
        QueryWrapper<ActMenu> wrapper = new QueryWrapper<>();
        if(StringUtils.isNotEmpty(name)){
            wrapper.like("name",name);
        }
        wrapper.orderByDesc("gmt_create");
        IPage<ActMenu> iPage = new Page<>(page,size);
        this.page(iPage,wrapper);
        List<ActMenu> list = iPage.getRecords();
        long total = iPage.getTotal();
        Map<String,Object> item= new HashMap<>();
        item.put("total",total);
        item.put("list",list);
        return item;
    }
}
