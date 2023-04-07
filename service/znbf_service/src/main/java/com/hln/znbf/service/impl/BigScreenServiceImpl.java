package com.hln.znbf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hln.znbf.entity.Statistics;
import com.hln.znbf.mapper.BigScreenMapper;
import com.hln.znbf.mapper.StatisticsMapper;
import com.hln.znbf.service.BigScreenService;
import com.hln.znbf.vo.WishWorkVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BigScreenServiceImpl implements BigScreenService {

    @Autowired
    private BigScreenMapper bigScreenMapper;
    @Autowired
    private StatisticsMapper statisticsMapper;

    @Override
    public Map<String,Object> getFourData(String areaCode) {

        return bigScreenMapper.getFourData(areaCode);
    }

    @Override
    public Map<String, Object> getDoingInfo(String areaCode) {
          Long total = bigScreenMapper.getDoingTotal(areaCode);
        //获取继续中的列表
          List<WishWorkVo> list =  bigScreenMapper.getDoingList(areaCode);
          Map<String,Object> item = new HashMap<>();
          item.put("total",total);
          item.put("list",list);

          return item;
    }

    @Override
    public Map<String, Object> getThreeIndex(String areaCode) {
        Map<String,Object> item = bigScreenMapper.getThreeIndex(areaCode);
        return item;
    }

    @Override
    public List<Map<String,Object>> getMapData(String areaCode) {
        List<Map<String,Object>> item = bigScreenMapper.getMapData(areaCode);
        return item;
    }

    @Override
    public List<Map<String,Object>> getAreaList(String areaCode) {
        List<Map<String,Object>> item = bigScreenMapper.getAreaList(areaCode);
        return item;
    }
}
