package com.machaojin.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName biz_partsrepertory
 */
@TableName(value ="biz_partsrepertory")
@Data
public class BizPartsrepertory implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer partsrepid;

    /**
     * 
     */
    private Integer partsid;

    /**
     * 
     */
    private Integer partsreqcount;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}