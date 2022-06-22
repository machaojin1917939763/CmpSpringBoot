package com.machaojin.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.machaojin.domain.BizParts;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* @author machaojin
* @description 针对表【biz_parts】的数据库操作Mapper
* @createDate 2022-06-14 10:46:50
* @Entity com.machaojin.domain.BizParts
*/
@Mapper
public interface BizPartsMapper extends BaseMapper<BizParts> {
    /**
     * 分页查询配件库流水
     * @param page 分页
     * @param parts 条件构造器
     * @return 返回分页查询数据
     */
    Page<BizParts> getPartsInOutList(@Param("page") Page<BizParts> page, @Param("parts") BizParts parts);


}




