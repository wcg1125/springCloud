package com.hln.znbf.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 浙农帮扶——岗位详情
 * </p>
 *
 * @author wcg
 * @since 2022-12-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("znbf_work")
@ApiModel(value="Work对象", description="浙农帮扶——岗位详情")
public class Work implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "编码")
    private String areaCode;

    @ApiModelProperty(value = "岗位地区名称")
    private String areaName;

    @ApiModelProperty(value = "岗位名称")
    private String name;

    @ApiModelProperty(value = "单位名称")
    private String unit;

    @ApiModelProperty(value = "岗位类型")
    private String type;

    @ApiModelProperty(value = "薪资")
    private String salary;

    @ApiModelProperty(value = "体力要求")
    private String tlqd;

    @ApiModelProperty(value = "学历要求")
    private String xlyq;

    @ApiModelProperty(value = "工作地址")
    private String address;

    @ApiModelProperty(value = "联系人")
    private String linkman;

    @ApiModelProperty(value = "电话")
    private String mobile;

    @ApiModelProperty(value = "岗位介绍")
    private String introduction;

    @ApiModelProperty(value = "招聘人数")
    private Integer num;

    @ApiModelProperty(value = "结束时间")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endTime;

    @ApiModelProperty(value = "岗位状态")
    private String status;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill =FieldFill.INSERT_UPDATE)
    private Date gmtModified;


}
