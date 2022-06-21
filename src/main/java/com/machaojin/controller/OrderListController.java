package com.machaojin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.machaojin.common.Result;
import com.machaojin.domain.BizOrder;
import com.machaojin.domain.SysCode;
import com.machaojin.service.impl.BizOrderServiceImpl;

import com.machaojin.service.impl.SysCodeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author 马超金
 * @version 1.0
 * @date 2022/6/15 14:02
 */

@Slf4j
@RestController
@RequestMapping("/order")
public class OrderListController {
    @Autowired
    private BizOrderServiceImpl orderService;
    @Autowired
    private SysCodeServiceImpl codeService;

    /**
     * 分页查询订单状态
     * @param page 初始页面
     * @param pageSize 页面大小
     * @param code 订单编码
     * @param time 订单时间
     * @param status 订单状态
     * @return 返回订单列表
     */
    @GetMapping
    public Result<Page<BizOrder>> getOrderList(Integer page, Integer pageSize, String code, String time, String status){
        Page<BizOrder> orderPage = new Page<>(page,pageSize);
        LambdaQueryWrapper<BizOrder> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(!StringUtils.isEmpty(code),BizOrder::getOrdercode,code);
        lambdaQueryWrapper.like(!StringUtils.isEmpty(time),BizOrder::getOrderdate,time);
        lambdaQueryWrapper.like(!StringUtils.isEmpty(status),BizOrder::getOrderflag,status);
        orderService.page(orderPage,lambdaQueryWrapper);
        List<BizOrder> records = orderPage.getRecords();
        for (BizOrder record : records) {
            LambdaQueryWrapper<SysCode> codeLambdaQueryWrapper = new LambdaQueryWrapper<>();
            codeLambdaQueryWrapper.eq(SysCode::getCode,record.getOrderflag());
            SysCode code1 = codeService.getOne(codeLambdaQueryWrapper);
            record.setOrderflag(code1.getName());
        }


        return Result.success(orderPage);
    }

    /**
     * 根据id删除数据
     * @param id 产品id
     * @return 返回结果
     */
    @DeleteMapping("{id}")
    public Result<String> delete(@PathVariable String id){
        orderService.removeById(id);
        return Result.success("success");
    }
}
