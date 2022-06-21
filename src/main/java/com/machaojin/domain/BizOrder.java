package com.machaojin.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName biz_order
 */
@TableName(value ="biz_order")
@Data
public class BizOrder implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer orderid;

    /**
     * 
     */
    private String ordercode;

    /**
     * 
     */
    private String orderdate;

    /**
     * 
     */
    private String orderflag;

    @TableField("IsDelete")
    @TableLogic
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}