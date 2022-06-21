package com.machaojin.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName biz_partsrepbill
 */
@TableName(value ="biz_partsrepbill")
@Data
public class BizPartsrepbill implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer billid;

    /**
     * 
     */
    private String billflag;

    /**
     * 
     */
    private String billtype;

    /**
     * 
     */
    private Integer partsid;

    /**
     * 
     */
    private Integer billcount;

    /**
     * 
     */
    private String billtime;

    /**
     * 
     */
    private Integer billuser;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}