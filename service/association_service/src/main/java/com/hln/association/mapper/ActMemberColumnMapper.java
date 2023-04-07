package com.hln.association.mapper;

import com.hln.association.entity.ActMemberColumn;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wcg
 * @since 2022-11-03
 */
public interface ActMemberColumnMapper extends BaseMapper<ActMemberColumn> {

    void addMemberColumnInfo(List<ActMemberColumn> members, Integer id);

    void updateMemberColumnInfo(List<ActMemberColumn> members);
}
