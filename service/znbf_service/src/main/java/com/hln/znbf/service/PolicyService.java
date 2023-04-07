package com.hln.znbf.service;

import com.hln.znbf.entity.Policy;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 * 浙农帮扶——政策 服务类
 * </p>
 *
 * @author wcg
 * @since 2022-12-01
 */
public interface PolicyService extends IService<Policy> {

    Map<String, Object> getMyPolicyList(HttpServletRequest request, String title ,String type,String category);

    Policy getMyPolicyById(String id);

    Map<String, Object> getPolicyList(String areaCode, String unit, String title,Integer pageNum,Integer pageSize);
}
