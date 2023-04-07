package com.hln.znbf.service;

import com.hln.znbf.entity.Statistics;

import java.util.List;
import java.util.Map;

public interface BigScreenService {

    Map<String,Object> getFourData(String areaCode);

    Map<String, Object> getDoingInfo(String areaCode);

    Map<String, Object> getThreeIndex(String areaCode);

    List<Map<String,Object>> getMapData(String areaCode);

    List<Map<String,Object>> getAreaList(String areaCode);
}
