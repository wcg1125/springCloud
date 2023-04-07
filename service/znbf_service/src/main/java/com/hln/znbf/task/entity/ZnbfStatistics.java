package com.hln.znbf.task.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data

public class ZnbfStatistics {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 地区编码
     */
    private String areaCode;
    /**
     * 人口数
     */
    private Integer rks;
    /**
     * 政策数
     */
    private Integer policyCount;
    /**
     * 岗位数
     */
    private Integer postCount;
    /**
     * 进行中微心愿个数
     */
    private Integer wishJxzCount;
    /**
     * 已完成微心愿个数
     */
    private Integer wishYwcCount;
    /**
     * 申请中岗位数
     */
    private Integer workJxzCount;
    /**
     * 已完成岗位数
     */
    private  Integer workYwcCount;
}
