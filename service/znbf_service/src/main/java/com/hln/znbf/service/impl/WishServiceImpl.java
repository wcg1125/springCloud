package com.hln.znbf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hln.commonuntils.JwtUtils;
import com.hln.commonuntils.ResultCode;
import com.hln.servicebase.exceptionhandler.PracticeException;
import com.hln.znbf.entity.Dsrnh;
import com.hln.znbf.entity.Policy;
import com.hln.znbf.entity.Wish;
import com.hln.znbf.mapper.WishMapper;
import com.hln.znbf.service.DsrnhService;
import com.hln.znbf.service.WishService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hln.znbf.vo.WishVo;
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
 * @since 2022-12-04
 */
@Service
public class WishServiceImpl extends ServiceImpl<WishMapper, Wish> implements WishService {



    @Autowired
    private DsrnhService dsrnhService;

    @Override
    public Map<String, Object> getMyWishCount(HttpServletRequest request) {
        String userId = JwtUtils.getIdByJwtToken(request);
        //获取登录用户id
        if(StringUtils.isEmpty(userId)){
            throw new PracticeException(ResultCode.ERROR,"参数错误！");
        }
        Dsrnh dsrnh = dsrnhService.getById(userId);
        if(dsrnh==null){
            throw new PracticeException(ResultCode.ERROR,"用户不存在！");
        }
        QueryWrapper<Wish> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        int count = this.count(wrapper);
        Map<String,Object> map = new HashMap<>();
        map.put("total",count);
        map.put("name",dsrnh.getXm());
        map.put("userId",dsrnh.getId());
        return map;
    }

    @Override
    public void shareMyWish(Wish wish) {
        //分享心愿
        wish.setProcess("1");
        wish.setStatus("N");
        this.save(wish);
    }

    @Override
    public Map<String, Object> getMyWishList(String userId, Integer pageNum, Integer pageSize) {
        QueryWrapper<Wish> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId)
                .orderByDesc("gmt_create");
        IPage<Wish> iPage = new Page<>(pageNum,pageSize);
        this.page(iPage,wrapper);
        Map<String,Object> map = new HashMap<>();
        map.put("total", iPage.getTotal());
        map.put("item", iPage.getRecords());
        return map;
    }

    @Override
    public Map<String, Object> getWishList(String areaCode, String name, String process, String status, Integer pageNum, Integer pageSize) {
        Long total = this.baseMapper.getWishCount(areaCode,name,process,status);
        Integer index = (pageNum-1)*pageSize;
        List<WishVo> list = this.baseMapper.getWishList(areaCode,name,process,status,index,pageSize);
        Map<String,Object> map = new HashMap<>();
        map.put("total", total);
        map.put("item", list);
        return map;
    }

    @Override
    public WishVo getWishDetails(Integer wishId) {
        return this.baseMapper.getWishDetails(wishId);
    }
}
