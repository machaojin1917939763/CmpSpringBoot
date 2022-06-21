package com.machaojin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.machaojin.domain.SysCustomer;
import com.machaojin.service.SysCustomerService;
import com.machaojin.mapper.SysCustomerMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
* @author machaojin
* @description 针对表【sys_customer】的数据库操作Service实现
* @createDate 2022-06-14 10:46:50
*/
@Service
public class SysCustomerServiceImpl extends ServiceImpl<SysCustomerMapper, SysCustomer>
    implements SysCustomerService{
    /**
     *  分页查询客户表信息
     * @param page 初始页面
     * @param pageSize 页面大小
     * @param name 输入框输入的信息
     * @return 返回分页查询的分结果page
     */
    @Override
    public Page<SysCustomer> getList(Integer page, Integer pageSize, String name) {
        LambdaQueryWrapper<SysCustomer> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(!StringUtils.isEmpty(name),SysCustomer::getName,name);
        Page<SysCustomer> pageInfo = new Page<>(page,pageSize);
        this.page(pageInfo,lambdaQueryWrapper);
        return pageInfo;
    }
}




