package com.hln.znbf.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 
 * </p>
 *
 * @author wcg
 * @since 2022-12-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("znbf_wish")
@ApiModel(value="Wish对象", description="")
public class Wish implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "农户id")
    private String userId;

    @ApiModelProperty(value = "心愿内容")
    private String content;

    @ApiModelProperty(value = "审核情况  Y ,N")
    private String audit;

    @ApiModelProperty(value = "审核时间")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date auditTime;

    @ApiModelProperty(value = "认领人联系方式")
    private String claim;

    @ApiModelProperty(value = "姓名")
    private String claimName;

    @ApiModelProperty(value = "是否达成  N,Y ")
    private String conclude;

    @ApiModelProperty(value = "达成时间")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date concludeTime;

    @ApiModelProperty(value = "反馈说明")
    private String feedback;

    @ApiModelProperty(value = "金额")
    private String amount;

    @ApiModelProperty(value = "流程 1分享心愿 2 心愿审核 3 心愿认领 4 达成情况")
    private String process;

    @ApiModelProperty(value = "是否终止 N ，Y")
    private String status;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill =FieldFill.INSERT_UPDATE)
    private Date gmtModified;


}
