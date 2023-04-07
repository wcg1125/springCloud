package com.hln.znbf.mapper;

import com.hln.znbf.entity.Work;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 浙农帮扶——岗位详情 Mapper 接口
 * </p>
 *
 * @author wcg
 * @since 2022-12-02
 */
public interface WorkMapper extends BaseMapper<Work> {

    List<Work> getWorkApplyList(String userId, Integer index, Integer pageSize);
}
