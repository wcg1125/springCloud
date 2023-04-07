package com.hln.association.service;

import com.hln.association.entity.ActLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wcg
 * @since 2022-11-08
 */
public interface ActLogService extends IService<ActLog> {

    Map<String,Object> getActLogList(String time);

    void addVisitCount();
}
