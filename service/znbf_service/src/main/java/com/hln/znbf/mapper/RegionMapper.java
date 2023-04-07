package com.hln.znbf.mapper;

import com.hln.znbf.entity.Region;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 地区信息表 Mapper 接口
 * </p>
 *
 * @author wcg
 * @since 2022-12-05
 */
public interface RegionMapper extends BaseMapper<Region> {

    List<Region> getAreaList(String areaCode);
}
