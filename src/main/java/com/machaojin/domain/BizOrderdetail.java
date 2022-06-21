package com.machaojin.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName biz_orderdetail
 */
@TableName(value ="biz_orderdetail")
@Data
public class BizOrderdetail implements Serializable {
    /**
     * 
     */
    @TableId
    private Integer orderdetailid;

    /**
     * 
     */
    private Integer partsid;

    /**
     * 
     */
    private Integer orderid;

    /**
     * 
     */
    private Integer orderpartscount;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}