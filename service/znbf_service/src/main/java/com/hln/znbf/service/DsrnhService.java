package com.hln.znbf.service;

import com.hln.znbf.entity.Dsrnh;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hln.znbf.entity.Region;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wcg
 * @since 2022-11-18
 */
public interface DsrnhService extends IService<Dsrnh> {

    Dsrnh getMineInfo(HttpServletRequest request);

    Map<String,Object> getDsrnhList(String areaCode, String sfzh, String type, String name, Integer pageNum, Integer pageSize);

    List<Region> getAreaList(String areaCode);

    Dsrnh getByuserId(String userId);
}
