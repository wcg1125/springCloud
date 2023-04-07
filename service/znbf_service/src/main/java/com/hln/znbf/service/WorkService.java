package com.hln.znbf.service;

import com.hln.znbf.entity.Work;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 * 浙农帮扶——岗位详情 服务类
 * </p>
 *
 * @author wcg
 * @since 2022-12-02
 */
public interface WorkService extends IService<Work> {

    Map<String, Object> getMyWorkList(HttpServletRequest request, String name, Integer pageNum, Integer pageSize);

    void applyWork(Integer workId, HttpServletRequest request);

    Map<String, Object> getWorkList(String areaCode, String name, Integer pageNum, Integer pageSize);

    Map<String, Object> getMyApplyList(HttpServletRequest request, Integer pageNum, Integer pageSize);

    Map<String, Object> getApplyList(String workName, String name, Integer pageNum, Integer pageSize, String status);

    void updateWork(Work work);

    Work getWorkInfo(Integer workId);
}
