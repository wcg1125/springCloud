package com.hln.znbf.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class WishWorkVo {

    private String xm;

    private String sfzh;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date time;

    private String type;

    private String status;
}
