package com.hln.znbf.controller;


import com.hln.commonuntils.R;
import com.hln.znbf.entity.Wish;
import com.hln.znbf.service.WishService;
import com.hln.znbf.service.WorkService;
import com.hln.znbf.vo.WishVo;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wcg
 * @since 2022-12-04
 */
@RestController
@RequestMapping("/znbf/wish")
public class WishController {


    @Autowired
    private WishService wishService;

    @GetMapping("getMyWishCount")
    public R getMyWishCount (HttpServletRequest request){
        Map<String,Object> item =  wishService.getMyWishCount(request);
        return R.ok().data("item",item);
    }

    /**
     * 分享心愿
     */

    @PostMapping("shareMyWish")
    public R shareMyWish (Wish wish){
         wishService.shareMyWish(wish);
        return R.ok();
    }

    /**
     * 我的微心愿列表
     */
    @GetMapping("getMyWishList")
    public R getMyWishList (String userId,Integer pageNum,Integer pageSize){
        Map<String,Object> item =  wishService.getMyWishList(userId,pageNum,pageSize);
        return R.ok().data("item",item);
    }


    /**
     * 微心愿终止，审核，认领，达成
     */
    @PostMapping("updateWishInfo")
    public R updateWishInfo (Wish wish){
        wishService.updateById(wish);
        return R.ok();
    }


    /**
     * 后台心愿列表
     */
    @GetMapping("getWishList")
    public R getWishList (String areaCode, String name, String process, String status, Integer pageNum, Integer pageSize){
        Map<String,Object> item =  wishService.getWishList(areaCode,name,process,status,pageNum,pageSize);
        return R.ok().data("item",item);
    }

    /**
     * 心愿详情
     */
    @GetMapping("getWishDetails/{wishId}")
    public R getWishDetails (@PathVariable Integer wishId){
        WishVo item =  wishService.getWishDetails(wishId);
        return R.ok().data("item",item);
    }
}

