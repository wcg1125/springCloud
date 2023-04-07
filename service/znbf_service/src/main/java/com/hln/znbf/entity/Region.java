package com.hln.znbf.entity;

import java.math.BigDecimal;
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
 * 地区信息表
 * </p>
 *
 * @author wcg
 * @since 2022-12-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("znbf_region")
@ApiModel(value="Region对象", description="地区信息表")
public class Region implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "uuid", type = IdType.ID_WORKER_STR)
    private String uuid;

    @ApiModelProperty(value = "节点类型")
    private String nodetype;

    @ApiModelProperty(value = "是否叶子节点 (0:否;1:是)")
    private BigDecimal istreeleaf;

    @ApiModelProperty(value = "树节点等级")
    private BigDecimal treelevel;

    @ApiModelProperty(value = "树节点排序")
    private BigDecimal treeorder;

    @ApiModelProperty(value = "树节点路径")
    private String treepath;

    @ApiModelProperty(value = "行政区划编码")
    private String code;

    @ApiModelProperty(value = "行政区划名称")
    private String name;

    @ApiModelProperty(value = "父节点ID")
    private String parentuuid;

    @ApiModelProperty(value = "经度")
    private String jd;

    @ApiModelProperty(value = "纬度")
    private String wd;

    @ApiModelProperty(value = "状态 (0:失效;1:有效)")
    private BigDecimal status;

    @ApiModelProperty(value = "是否重点帮扶村Y 是，N否")
    private String type;

    @ApiModelProperty(value = "革命老区")
    private String isgmlq;

    @ApiModelProperty(value = "是否山区县")
    private String sfsqx;

    @ApiModelProperty(value = "地区简称名称")
    private String jcname;


}
