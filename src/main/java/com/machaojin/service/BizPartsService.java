package com.machaojin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.machaojin.domain.BizParts;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

/**
* @author machaojin
* @description 针对表【biz_parts】的数据库操作Service
* @createDate 2022-06-14 10:46:50
*/
public interface BizPartsService extends IService<BizParts> {
    /**
     * 分页查询配件库流水
     * @param page 分页
     * @param parts 条件构造器
     */
    void getPartsInOutList(@Param("page") Page<BizParts> page, @Param("parts") BizParts parts);

}
