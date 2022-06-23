package com.machaojin.mapper;

import com.machaojin.domain.BizProduct;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author machaojin
* @description 针对表【biz_product】的数据库操作Mapper
* @createDate 2022-06-22 16:02:18
* @Entity com.machaojin.domain.BizProduct
*/
@Mapper
public interface BizProductMapper extends BaseMapper<BizProduct> {

}




