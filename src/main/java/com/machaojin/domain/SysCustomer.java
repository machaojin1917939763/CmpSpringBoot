package com.machaojin.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName sys_customer
 */
@TableName(value ="sys_customer")
@Data
public class SysCustomer implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private String tel;

    /**
     * 
     */
    private String carno;

    /**
     * 
     */
    private String cartype;

    /**
     * 
     */
    private String createdate;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}