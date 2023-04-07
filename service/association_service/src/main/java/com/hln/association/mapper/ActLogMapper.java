package com.hln.association.mapper;

import com.hln.association.entity.ActLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wcg
 * @since 2022-11-08
 */
public interface ActLogMapper extends BaseMapper<ActLog> {

    List<ActLog> getActLogList(String time, String flag);

    Map<String, Object> getAddData();

    ActLog getThisLog();
}
