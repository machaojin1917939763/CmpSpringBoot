package com.machaojin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.machaojin.common.Result;
import com.machaojin.domain.SysCustomer;
import com.machaojin.service.impl.SysCustomerServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author 马超金
 * @version 1.0
 * @date 2022/6/14 10:49
 */

@Slf4j
@RestController
@RequestMapping("/customer")
@CrossOrigin
public class CustomerController {
    @Autowired
    private SysCustomerServiceImpl customerService;

    /**
     * 初始化当前客户信息
     * @param page 初始页面大小
     * @param pageSize 分页大小
     * @param name 检索信息
     * @return 返回分页查询信息
     */
    @GetMapping
    public Result<Page<SysCustomer>> getListCustomer(Integer page, Integer pageSize, String name){
        return Result.success(customerService.getList(page,pageSize,name));
    }

    /**
     * 添加客户/编辑客户
     * @param customer 客户信息
     * @return 返回修改结果
     */
    @PostMapping
    public Result<String> addCustomer(@RequestBody SysCustomer customer){
        System.out.println(customer);
        LambdaQueryWrapper<SysCustomer> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SysCustomer::getId,customer.getId());
        SysCustomer serviceOne = customerService.getOne(lambdaQueryWrapper);
        log.info("serviceOne{}",serviceOne);
        if (serviceOne == null){
            customer.setCreatedate(LocalDateTime.now().toString());
            customerService.save(customer);
            return Result.success("成功");
        }
        serviceOne.setCreatedate(LocalDateTime.now().toString());
        customerService.updateById(serviceOne);
        return Result.success("成功");
    }

    /**
     * 根据id删除用户
     * @param id 客户id
     * @return 返回删除信息
     */
    @DeleteMapping("{id}")
    public Result<String> deleteCustom(@PathVariable String id){
        customerService.removeById(id);
        return Result.success("成功");
    }

    /**
     * 根据id查询
     * @param id id
     * @return 返回查询到的数据
     */
    @GetMapping("{id}")
    public Result<SysCustomer> getOne(@PathVariable String id){
        SysCustomer customer = customerService.getById(id);
        return Result.success(customer);
    }
}
