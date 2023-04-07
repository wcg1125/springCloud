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
 * @since 2022-11-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("znbf_dsrnh")
@ApiModel(value="Dsrnh对象", description="")
public class Dsrnh implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "农户类型")
    private String type;

    @ApiModelProperty(value = "地区编码")
    private String areacode;

    @ApiModelProperty(value = "地区名称")
    private String areaname;

    @ApiModelProperty(value = "所在地")
    private String szd;

    @ApiModelProperty(value = "家庭人口数")
    private String jtrks;

    @ApiModelProperty(value = "姓名")
    private String xm;

    @ApiModelProperty(value = "性别")
    private String xb;

    @ApiModelProperty(value = "身份证")
    private String sfzh;

    @ApiModelProperty(value = "密码")
    private String password;

    private String csrq;

    @ApiModelProperty(value = "与户主关系")
    private String yhzgx;

    @ApiModelProperty(value = "文化程度")
    private String whcd;

    @ApiModelProperty(value = "政治面貌")
    private String zzmm;

    @ApiModelProperty(value = "家庭状况")
    private String jkzk;

    @ApiModelProperty(value = "劳动能力")
    private String ldnl;

    @ApiModelProperty(value = "识别年度")
    private String sbnd;

    @ApiModelProperty(value = "贫困类型")
    private String pklx;

    @ApiModelProperty(value = "致贫原因")
    private String zpyy;

    @ApiModelProperty(value = "禁用启用")
    private String status;


}
