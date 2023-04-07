package com.hln.association.service;

import com.hln.association.entity.ActMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 职协-栏目管理 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-10-24
 */
public interface ActMenuService extends IService<ActMenu> {

    List<ActMenu> getNewType(Integer type);

    Map<String, Object> getMenuPage(String name, Integer page, Integer size);
}
