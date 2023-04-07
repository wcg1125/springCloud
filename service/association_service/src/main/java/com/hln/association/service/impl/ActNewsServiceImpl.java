package com.hln.association.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.hln.association.entity.ActNews;
import com.hln.association.mapper.ActNewsMapper;
import com.hln.association.service.ActNewsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hln.commonuntils.JwtUtils;
import com.hln.commonuntils.ResultCode;
import com.hln.servicebase.exceptionhandler.PracticeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 职协-新闻管理 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-10-24
 */
@Service
public class ActNewsServiceImpl extends ServiceImpl<ActNewsMapper, ActNews> implements ActNewsService {

    @Autowired
    private  ActNewsMapper actNewsMapper;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public Map<String, Object> getNewList(Integer page, Integer size,String type) {
        if(StringUtils.isEmpty(type) || page==null || size==null){
            throw new PracticeException(ResultCode.ERROR,"参数缺失！");
        }
        Integer total = actNewsMapper.getNewTotal(type);
        //获取起始位置下标
        Integer index = (page-1)*size;
        List<ActNews> list = actNewsMapper.getNewList(index,size,type);
        Map<String,Object> item= new HashMap<>();
        item.put("total",total);
        item.put("list",list);
        return item;
    }

    @Override
    public void addNews(ActNews actNews) {
        actNews.setIsTop("N");
        //获取当前最大的层级；新增是默认为1当前层级加1
        Long thisLevel = actNewsMapper.getLevelMax();
        actNews.setLevel(String.valueOf(thisLevel+1));
        actNewsMapper.insert(actNews);
    }

    @Override
    public void moveNew(Integer id,String status) {
        if(StringUtils.isEmpty(status) || id==null){
            throw new PracticeException(ResultCode.ERROR,"参数缺失！");
        }
        ActNews actNews = this.getById(id);
        String level = actNews.getLevel();
        //获取和气交换的数据对象
        ActNews moveNews = actNewsMapper.moveNews(level,status);
        if(moveNews==null){
            throw new PracticeException(ResultCode.ERROR,"当前数据无法上移下移！");
        }
        actNews.setLevel(moveNews.getLevel());
        moveNews.setLevel(level);
        actNewsMapper.updateById(actNews);
        actNewsMapper.updateById(moveNews);


    }

    @Override
    public List<ActNews> getNoticeList() {
        QueryWrapper<ActNews> wrapper = new QueryWrapper<>();
        wrapper.eq("type","8");
        return this.baseMapper.selectList(wrapper);
    }

    @Override
    public List<ActNews> getNewByType(Integer oneId, Integer twoId) {
        if(oneId==null){
            throw new PracticeException(ResultCode.ERROR,"参数缺失！");
        }
        //会员需要登录
        if(oneId==7){
            //判断是否登录
            if(!isLogin()){
               throw new PracticeException(ResultCode.tokenCode,"未登录或者登录失效！");
            }
        }
        QueryWrapper<ActNews> wrapper = new QueryWrapper<>();
        wrapper.eq("type",oneId)
                .orderByDesc("gmt_create");
        if(twoId!=null){
         wrapper.eq("m_id",twoId);
        }

        return this.baseMapper.selectList(wrapper);
    }


    /**
     * 判断是否登录
     * @return
     */
    private Boolean isLogin(){
        String token = request.getHeader("Authorization");
        if(StringUtils.isEmpty(token)){
           return false;
        }
        //解析token;判断token是否有效
        boolean flag = JwtUtils.checkToken(token);
        if(flag){
            boolean redisTokenStatus = redisTemplate.hasKey(token);
            if(!redisTokenStatus){
               return false;
            }
            //重置时间
            redisTemplate.expire(token,120L, TimeUnit.MINUTES);
        }else {
            return false;
        }
        return true;
    }
}
