package com.hln.association.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * 创建excel对应的实体类
 *
 */

@Data
public class CretData {

    @ExcelProperty(index = 0)
    private String name;


    @ExcelProperty(index = 1)
    private String code;

    @ExcelProperty(index = 2)
    private String title;

    @ExcelProperty(index = 3)
    private String unit;

    private String reason;

}
