package com.hln.znbf.mapper;


import com.hln.znbf.vo.WishWorkVo;

import java.util.List;
import java.util.Map;

public interface BigScreenMapper {

    Long getDoingTotal(String areaCode);

    List<WishWorkVo> getDoingList(String areaCode);

    Map<String, Object> getThreeIndex(String areaCode);

    List<Map<String,Object>> getMapData(String areaCode);

    List<Map<String, Object>> getAreaList(String areaCode);

    Map<String, Object> getFourData(String areaCode);
}
