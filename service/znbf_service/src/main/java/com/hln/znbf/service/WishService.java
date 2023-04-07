package com.hln.znbf.service;

import com.hln.znbf.entity.Wish;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hln.znbf.vo.WishVo;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wcg
 * @since 2022-12-04
 */
public interface WishService extends IService<Wish> {

    Map<String, Object> getMyWishCount(HttpServletRequest request);

    void shareMyWish(Wish wish);

    Map<String, Object> getMyWishList(String userId, Integer pageNum, Integer pageSize);

    Map<String, Object> getWishList(String areaCode, String name, String process, String status, Integer pageNum, Integer pageSize);

    WishVo getWishDetails(Integer wishId);
}
