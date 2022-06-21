package com.machaojin.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @author machaojin
 * @TableName biz_assemble
 */
@TableName(value ="biz_assemble")
@Data
public class BizAssemble implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer assembleid;

    /**
     * 
     */
    private Integer partsid;

    /**
     * 
     */
    private Integer productid;

    /**
     * 
     */
    private Integer partscount;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}