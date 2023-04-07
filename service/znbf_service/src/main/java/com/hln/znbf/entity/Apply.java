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
 * 浙农帮扶——岗位投递
 * </p>
 *
 * @author wcg
 * @since 2022-12-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("znbf_apply")
@ApiModel(value="Apply对象", description="浙农帮扶——岗位投递")
public class Apply implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "投递人id")
    private String userId;

    @ApiModelProperty(value = "岗位id")
    private Integer workId;

    @ApiModelProperty(value = "状态")
    private String status;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill =FieldFill.INSERT_UPDATE)
    private Date gmtModified;


}
