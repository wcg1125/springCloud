package com.hln.association.service;

import com.hln.association.entity.ActMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hln.association.entity.ActMemberColumn;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 职协-会员管理 服务类
 * </p>
 *
 * @author wcg
 * @since 2022-11-02
 */
public interface ActMemberService extends IService<ActMember> {

    Map<String, Object> getMemberPage(String account, Integer page, Integer size);

    void removeMember(Integer id);

    void addMember(List<ActMemberColumn> members);

    List<ActMemberColumn> getColumnInfo(Integer id);

    void updateMember(List<ActMemberColumn> members);

    List<ActMemberColumn> getMemberInfo(String memberId);
}
