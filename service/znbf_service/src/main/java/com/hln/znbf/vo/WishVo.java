package com.hln.znbf.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class WishVo {


    @ApiModelProperty(value = "心愿id")
    private Integer wishId;

    @ApiModelProperty(value = "地区编码")
    private String areaCode;

    @ApiModelProperty(value = "地区名称")
    private String areaName;

    @ApiModelProperty(value = "所在地")
    private String szd;

    @ApiModelProperty(value = "姓名")
    private String xm;

    @ApiModelProperty(value = "身份证")
    private String sfzh;


    @ApiModelProperty(value = "心愿内容")
    private String content;

    @ApiModelProperty(value = "流程 1分享心愿 2 心愿审核 3 心愿认领 4 达成情况")
    private String process;

    @ApiModelProperty(value = "是否终止 N ，Y")
    private String status;

    @ApiModelProperty(value = "审核情况  Y ,N")
    private String audit;

    @ApiModelProperty(value = "审核时间")
    private Date auditTime;

    @ApiModelProperty(value = "认领人联系方式")
    private String claim;

    @ApiModelProperty(value = "认领人姓名")
    private String claimName;

    @ApiModelProperty(value = "是否达成  N,Y ")
    private String conclude;

    @ApiModelProperty(value = "达成时间")
    private String concludeTime;

    @ApiModelProperty(value = "反馈说明")
    private String feedback;

    @ApiModelProperty(value = "涉及金额")
    private String amount;

    @ApiModelProperty(value = "创建时间")
    private Date gmtCreate;
}
