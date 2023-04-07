package com.hln.association.service;

import com.hln.association.entity.ActNews;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 职协-新闻管理 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-10-24
 */
public interface ActNewsService extends IService<ActNews> {

    Map<String, Object> getNewList(Integer page, Integer size,String type);

    void addNews(ActNews actNews);

    void moveNew(Integer id,String status);

    List<ActNews> getNoticeList();

    List<ActNews> getNewByType(Integer oneId, Integer twoId);
}
