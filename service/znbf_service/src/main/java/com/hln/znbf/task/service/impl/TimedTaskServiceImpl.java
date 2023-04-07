package com.hln.znbf.task.service.impl;

import com.hln.znbf.mapper.TimedTaskMapper;
import com.hln.znbf.task.entity.ZnbfStatistics;
import com.hln.znbf.task.service.TimedTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimedTaskServiceImpl implements TimedTaskService {

    @Autowired
    private TimedTaskMapper timedTaskMapper;

    @Override
    public void dsrnhTask() {
       List<ZnbfStatistics> list =  timedTaskMapper.dsrnhTask();
       //清空表
        timedTaskMapper.truncateTable();
        //批量新增
        timedTaskMapper.saveList(list);
    }
}
