package com.hln.association.service.impl;

import com.hln.association.entity.ActLog;
import com.hln.association.mapper.ActLogMapper;
import com.hln.association.service.ActLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wcg
 * @since 2022-11-08
 */
@Service
public class ActLogServiceImpl extends ServiceImpl<ActLogMapper, ActLog> implements ActLogService {


    @Override
    public Map<String,Object> getActLogList(String time) {
        //获取当前月份
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM");
        Date date = new Date(System.currentTimeMillis());
        String thisTime = formatter.format(date);
        String flag = "other";
        if(thisTime.equals(time)) {
            flag = "this";
        }
        List<ActLog> logs = this.baseMapper.getActLogList(time+"-01",flag);
        //获取累计
        Map<String,Object> item = this.baseMapper.getAddData();
        item.put("list",logs);
        return item;
    }

    @Override
    public void addVisitCount() {
       ActLog actLog = this.baseMapper.getThisLog();
       if(actLog==null){
           actLog=new ActLog();
           actLog.setLoginCount(0).setVisitCount(1);
           this.save(actLog);
       }else {
           actLog.setVisitCount(actLog.getVisitCount()+1);
           this.updateById(actLog);
       }
    }
}
