package com.hln.association.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.hln.association.entity.ActFile;
import com.hln.association.mapper.ActFileMapper;
import com.hln.association.service.ActFileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wcg
 * @since 2022-11-07
 */
@Service
public class ActFileServiceImpl extends ServiceImpl<ActFileMapper, ActFile> implements ActFileService {

    @Override
    public void saveActFile(List<ActFile> actFiles, String type) {
        if(StringUtils.isEmpty(type)){
            type="1";
        }
        //删除对应的类型
        QueryWrapper<ActFile> wrapper = new QueryWrapper<>();
        wrapper.eq("type",type);
        this.remove(wrapper);
        for (ActFile af:actFiles){
            af.setType(type);
        }
        this.saveBatch(actFiles);
    }

    @Override
    public List<ActFile> getImgList(String type) {
        //删除对应的类型
        QueryWrapper<ActFile> wrapper = new QueryWrapper<>();
        wrapper.eq("type",type)
                .orderByAsc("sort");
        return this.baseMapper.selectList(wrapper);
    }
}
