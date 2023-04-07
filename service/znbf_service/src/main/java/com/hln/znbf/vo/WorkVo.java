package com.hln.znbf.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class WorkVo {

    @ApiModelProperty(value = "岗位id")
    private Integer workId;

    @ApiModelProperty(value = "姓名")
    private String xm;

    @ApiModelProperty(value = "性别")
    private String xb;

    @ApiModelProperty(value = "岗位名称")
    private String name;

    @ApiModelProperty(value = "单位名称")
    private String unit;

    @ApiModelProperty(value = "岗位类型")
    private String type;

    @ApiModelProperty(value = "申请状态")
    private String status;

    @ApiModelProperty(value = "申请时间")
    private Date gmtCreate;




}
