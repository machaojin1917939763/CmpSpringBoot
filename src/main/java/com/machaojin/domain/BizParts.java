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
 * @TableName biz_parts
 */
@TableName(value ="biz_parts")
@Data
public class BizParts implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer partsid;

    /**
     * 
     */
    private String partsname;

    /**
     * 
     */
    private String partsmodel;

    /**
     * 
     */
    private String partsloc;

    /**
     * 
     */
    private String partsprodate;

    /**
     * 
     */
    private String partsremark;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}