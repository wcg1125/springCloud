package com.hln.association.mapper;

import com.hln.association.entity.ActNews;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 职协-新闻管理 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2022-10-24
 */
public interface ActNewsMapper extends BaseMapper<ActNews> {

    Integer getNewTotal(String type);

    List<ActNews> getNewList(Integer index, Integer size, String type);

    Long getLevelMax();

    ActNews moveNews(String level, String status);
}
