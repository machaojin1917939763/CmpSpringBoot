package com.machaojin.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.machaojin.domain.BizParts;
import com.machaojin.service.BizPartsService;
import com.machaojin.mapper.BizPartsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author machaojin
* @description 针对表【biz_parts】的数据库操作Service实现
* @createDate 2022-06-14 10:46:50
*/
@Service
public class BizPartsServiceImpl extends ServiceImpl<BizPartsMapper, BizParts>
    implements BizPartsService{
    @Autowired
    private BizPartsMapper mapper;

    /**
     * 分页查询配件库流水
     * @param page 分页
     * @param parts 条件构造器
     */
    @Override
    public void getPartsInOutList(Page<BizParts> page, BizParts parts) {
        mapper.getPartsInOutList(page, parts);
    }
}




