package com.machaojin.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName biz_productrepertory
 */
@TableName(value ="biz_productrepertory")
@Data
public class BizProductrepertory implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer prorepid;

    /**
     * 
     */
    private Integer productid;

    /**
     * 
     */
    private Integer prorepcount;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}