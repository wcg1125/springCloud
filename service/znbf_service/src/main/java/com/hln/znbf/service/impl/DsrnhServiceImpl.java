package com.hln.znbf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hln.commonuntils.JwtUtils;
import com.hln.commonuntils.ResultCode;
import com.hln.servicebase.exceptionhandler.PracticeException;
import com.hln.znbf.constants.DsrnhConstants;
import com.hln.znbf.entity.Dsrnh;
import com.hln.znbf.entity.Policy;
import com.hln.znbf.entity.Region;
import com.hln.znbf.mapper.DsrnhMapper;
import com.hln.znbf.mapper.RegionMapper;
import com.hln.znbf.service.DsrnhService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wcg
 * @since 2022-11-18
 */
@Service
public class DsrnhServiceImpl extends ServiceImpl<DsrnhMapper, Dsrnh> implements DsrnhService {

    @Autowired
    private RegionMapper regionMapper;

    @Override
    public Dsrnh getMineInfo(HttpServletRequest request) {
        String userId = JwtUtils.getIdByJwtToken(request);
        if(StringUtils.isEmpty(userId)){
            throw new PracticeException(ResultCode.ERROR,"参数错误！");
        }

        DsrnhConstants.initialize();
        Dsrnh dsrnh = this.getById(userId);
        //常量类赋值
        dsrnh.setType(DsrnhConstants.TYPE.get(dsrnh.getType()));
        dsrnh.setWhcd(DsrnhConstants.WHCD.get(dsrnh.getWhcd()));
        dsrnh.setZzmm(DsrnhConstants.ZZMM.get(dsrnh.getZzmm()));
        dsrnh.setJkzk(DsrnhConstants.JKZK.get(dsrnh.getJkzk()));
        dsrnh.setLdnl(DsrnhConstants.LDNL.get(dsrnh.getLdnl()));
        dsrnh.setPklx(DsrnhConstants.PKLX.get(dsrnh.getPklx()));
        dsrnh.setZpyy(DsrnhConstants.ZPYY.get(dsrnh.getZpyy()));
        dsrnh.setYhzgx(DsrnhConstants.YHZGX.get(dsrnh.getYhzgx()));

        return dsrnh;
    }

    @Override
    public Map<String,Object> getDsrnhList(String areaCode, String sfzh, String type, String name, Integer pageNum, Integer pageSize) {
        if(pageNum==null || pageSize==null){
            throw new PracticeException(ResultCode.ERROR,"参数缺失！");
        }
        QueryWrapper<Dsrnh> wrapper = new QueryWrapper<>();
        if(StringUtils.isNotEmpty(areaCode)) {
            wrapper.likeRight("area_code",areaCode);
        }
        if(StringUtils.isNotEmpty(sfzh)) {
            wrapper.eq("sfzh",sfzh);
        }
        if(StringUtils.isNotEmpty(type)) {
            wrapper.eq("type",type);
        }
        if(StringUtils.isNotEmpty(name)) {
            wrapper.like("xm",name);
        }
        IPage<Dsrnh> iPage = new Page<>(pageNum,pageSize);
        this.page(iPage,wrapper);
        Map<String,Object> map = new HashMap<>();

        DsrnhConstants.initialize();
        List<Dsrnh> list = iPage.getRecords();
        for (Dsrnh dsrnh:list){
            //常量类赋值
            dsrnh.setType(DsrnhConstants.TYPE.get(dsrnh.getType()));
            dsrnh.setWhcd(DsrnhConstants.WHCD.get(dsrnh.getWhcd()));
            dsrnh.setZzmm(DsrnhConstants.ZZMM.get(dsrnh.getZzmm()));
            dsrnh.setJkzk(DsrnhConstants.JKZK.get(dsrnh.getJkzk()));
            dsrnh.setLdnl(DsrnhConstants.LDNL.get(dsrnh.getLdnl()));
            dsrnh.setPklx(DsrnhConstants.PKLX.get(dsrnh.getPklx()));
            dsrnh.setZpyy(DsrnhConstants.ZPYY.get(dsrnh.getZpyy()));
            dsrnh.setYhzgx(DsrnhConstants.YHZGX.get(dsrnh.getYhzgx()));
        }
        map.put("total", iPage.getTotal());
        map.put("item", list);
        return map;
    }

    @Override
    public List<Region> getAreaList(String areaCode) {
        List<Region> areas =regionMapper.getAreaList(areaCode);
        return areas;
    }

    @Override
    public Dsrnh getByuserId(String userId) {
        DsrnhConstants.initialize();
        Dsrnh dsrnh = this.getById(userId);
        //常量类赋值
        dsrnh.setType(DsrnhConstants.TYPE.get(dsrnh.getType()));
        dsrnh.setWhcd(DsrnhConstants.WHCD.get(dsrnh.getWhcd()));
        dsrnh.setZzmm(DsrnhConstants.ZZMM.get(dsrnh.getZzmm()));
        dsrnh.setJkzk(DsrnhConstants.JKZK.get(dsrnh.getJkzk()));
        dsrnh.setLdnl(DsrnhConstants.LDNL.get(dsrnh.getLdnl()));
        dsrnh.setPklx(DsrnhConstants.PKLX.get(dsrnh.getPklx()));
        dsrnh.setZpyy(DsrnhConstants.ZPYY.get(dsrnh.getZpyy()));
        dsrnh.setYhzgx(DsrnhConstants.YHZGX.get(dsrnh.getYhzgx()));
        return dsrnh;
    }
}
