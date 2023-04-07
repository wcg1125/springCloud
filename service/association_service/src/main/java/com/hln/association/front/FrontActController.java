package com.hln.association.front;

import com.hln.association.entity.ActCertificate;
import com.hln.association.entity.ActFile;
import com.hln.association.entity.ActNews;
import com.hln.association.service.ActCertificateService;
import com.hln.association.service.ActFileService;
import com.hln.association.service.ActLogService;
import com.hln.association.service.ActNewsService;
import com.hln.commonuntils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Queue;

/**
 * 前台
 */
@RestController
@RequestMapping("/association/front")
public class FrontActController {

    @Autowired
    private ActLogService actLogService;
    @Autowired
    private ActNewsService actNewsService;
    @Autowired
    private ActFileService actFileService;
    @Autowired
    private ActCertificateService actCertificateService;


    /**
     * 添加访问人次
     * @return
     */
    @GetMapping("addVisitCount")
    public  R addVisitCount(){
        actLogService.addVisitCount();
        return R.ok();
    }

    /**
     * 站内公告
     */
    @GetMapping("getNoticeList")
    public  R getNoticeList(){
        List<ActNews> news =  actNewsService.getNoticeList();
        return R.ok().data("list",news);
    }


    /**
     * 获取图片集合
     * 2.轮播图
     * 1焦点图
     */

    @GetMapping("getImgList/{type}")
    public R  getImgList  (@PathVariable String type){
        List<ActFile> list = actFileService.getImgList(type);
        return R.ok().data("item",list);
    }

    /**
     * 查询证书号
     */
    @GetMapping("selectCret/{code}")
    public R  selectCret  (@PathVariable String code){
        ActCertificate  item = actCertificateService.selectCret(code);
        return R.ok().data("item",item);
    }
    /**
     * 获取所有的二级菜单
     */


    /**
     * 首页
     */


    /**
     * 获取内容
     */
    @GetMapping("getNewByType")
    public R   getNewByType(Integer oneId,Integer twoId) {
        List<ActNews> list = actNewsService.getNewByType(oneId,twoId);
        return R.ok().data("item",list);
    }

}
