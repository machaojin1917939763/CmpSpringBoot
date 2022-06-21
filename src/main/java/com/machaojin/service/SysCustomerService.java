package com.machaojin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.machaojin.domain.SysCustomer;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author machaojin
* @description 针对表【sys_customer】的数据库操作Service
* @createDate 2022-06-14 10:46:50
*/
public interface SysCustomerService extends IService<SysCustomer> {
    /**
     *  分页查询客户表信息
     * @param page 初始页面
     * @param pageSize 页面大小
     * @param name 输入框输入的信息
     * @return 返回分页查询的分结果page
     */
      Page<SysCustomer> getList(Integer page,Integer pageSize,String name);
}
