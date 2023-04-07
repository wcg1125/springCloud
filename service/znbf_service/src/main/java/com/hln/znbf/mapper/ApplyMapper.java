package com.hln.znbf.mapper;

import com.hln.znbf.entity.Apply;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hln.znbf.vo.WorkVo;

import java.util.List;

/**
 * <p>
 * 浙农帮扶——岗位投递 Mapper 接口
 * </p>
 *
 * @author wcg
 * @since 2022-12-02
 */
public interface ApplyMapper extends BaseMapper<Apply> {

    Integer getApplyCount(String workName, String name, String status);

    List<WorkVo> getApplyList(String workName, String name, String status, Integer index, Integer pageSize);
}
