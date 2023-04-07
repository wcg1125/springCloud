package com.hln.znbf.mapper;

import com.hln.znbf.entity.Wish;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hln.znbf.vo.WishVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wcg
 * @since 2022-12-04
 */
public interface WishMapper extends BaseMapper<Wish> {

    Long getWishCount(String areaCode, String name, String process, String status);

    List<WishVo> getWishList(String areaCode, String name, String process, String status, Integer index, Integer pageSize);

    WishVo getWishDetails(Integer wishId);
}
