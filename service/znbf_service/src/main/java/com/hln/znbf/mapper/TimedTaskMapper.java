package com.hln.znbf.mapper;

import com.hln.znbf.task.entity.ZnbfStatistics;

import java.util.List;

public interface TimedTaskMapper {

    List<ZnbfStatistics> dsrnhTask();

    void truncateTable();

    void saveList(List<ZnbfStatistics> list);
}
