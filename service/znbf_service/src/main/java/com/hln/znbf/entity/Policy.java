package com.hln.znbf.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 浙农帮扶——政策
 * </p>
 *
 * @author wcg
 * @since 2022-12-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("znbf_policy")
@ApiModel(value="Policy对象", description="浙农帮扶——政策")
public class Policy implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "地区名称")
    private String areaCode;

    @ApiModelProperty(value = "地区编码")
    private String areaName;

    @ApiModelProperty(value = "事项名称")
    private String title;

    @ApiModelProperty(value = "补贴类型")
    private String type;

    @ApiModelProperty(value = "发放频率")
    private String frequency;

    @ApiModelProperty(value = "单位名称")
    private String unit;

    @ApiModelProperty(value = "帮扶类型")
    private String category;

    @ApiModelProperty(value = "是否普惠性")
    private String inclusive;

    @ApiModelProperty(value = "层级")
    private String levle;

    @ApiModelProperty(value = "农户类型")
    private String dsrnhType;

    @ApiModelProperty(value = "文化程度")
    private String whcd;

    @ApiModelProperty(value = "健康状况")
    private String jkzk;

    @ApiModelProperty(value = "事项内容")
    private String content;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill =FieldFill.INSERT_UPDATE)
    private Date gmtModified;


}
