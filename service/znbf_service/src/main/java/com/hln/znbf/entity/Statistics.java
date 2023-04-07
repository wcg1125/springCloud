package com.hln.znbf.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author wcg
 * @since 2022-12-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("znbf_statistics")
@ApiModel(value="Statistics对象", description="")
public class Statistics implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "地区编码")
    private String areaCode;

    @ApiModelProperty(value = "人口数")
    private Integer rks;

    @ApiModelProperty(value = "政策数")
    private Integer policyCount;

    @ApiModelProperty(value = "岗位数")
    private Integer postCount;

    @ApiModelProperty(value = "进行中心愿数")
    private Integer wishJxzCount;

    @ApiModelProperty(value = "已完成心愿数")
    private Integer wishYwcCount;

    @ApiModelProperty(value = "进行中就业数")
    private Integer workJxzCount;

    @ApiModelProperty(value = "已完成就业数")
    private Integer workYwcCount;


}
