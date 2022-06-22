package com.machaojin.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName biz_product
 */
@TableName(value ="biz_product")
@Data
public class BizProduct implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer productid;


    private String productcode;

    /**
     * 
     */
    private String productname;

    /**
     * 
     */
    private String productremark;

    @TableField("IsDelete")
    @TableLogic
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}