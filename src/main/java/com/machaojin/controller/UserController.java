package com.machaojin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.machaojin.common.Result;
import com.machaojin.datatanobject.UserEmpDto;
import com.machaojin.domain.SysEmp;
import com.machaojin.domain.SysUser;
import com.machaojin.service.impl.SysEmpServiceImpl;
import com.machaojin.service.impl.SysUserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 马超金
 * @version 1.0
 * @date 2022/6/14 17:15
 */

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private SysUserServiceImpl userService;
    @Autowired
    private SysEmpServiceImpl empService;

    /**
     *  链表查询用户信息
     * @param page 初始页
     * @param pageSize 分页大小
     * @param name 检索内容
     * @return 返回分页查询数据
     */
    @GetMapping
    public Result<Page<SysEmp>> getEmpList(Integer page, Integer pageSize, String name,String username){
        Page<SysUser> userPage = new Page<>(page,pageSize);

        LambdaQueryWrapper<SysUser> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(!StringUtils.isEmpty(name),SysUser::getLoginname,name);
        userService.page(userPage,lambdaQueryWrapper);
        List<SysUser> records = userPage.getRecords();

        Page<SysEmp> empPage = new Page<>(page,pageSize);
        //进行对象拷贝
        BeanUtils.copyProperties(userPage,empPage,"records");

        List<SysEmp> empList = new ArrayList<>();
        for (SysUser record : records) {
            LambdaQueryWrapper<SysEmp> lambdaQueryWrapper1 = new LambdaQueryWrapper<>();
            lambdaQueryWrapper1.eq(SysEmp::getId,record.getEid());
            lambdaQueryWrapper1.like(!StringUtils.isEmpty(username),SysEmp::getName,username);

            SysEmp sysEmp = empService.getOne(lambdaQueryWrapper1);
            //再次进行对象拷贝
            sysEmp.setDept(record.getLoginname());
            sysEmp.setHiredate(record.getLogintime());
            empList.add(sysEmp);
        }
        empPage.setRecords(empList);
        return Result.success(empPage);
    }

    /**
     * 添加用户
     * @param user 用户信息
     * @return 返回结果
     */
    @PostMapping
    public Result<String> addUser(@RequestBody UserEmpDto user){
        SysUser sysUser = new SysUser();
        SysEmp sysEmp = new SysEmp();

        BeanUtils.copyProperties(user,sysUser);
        BeanUtils.copyProperties(user,sysEmp);

        log.info("sysEmp{}",sysEmp);
        log.info("sysUser{}",sysUser);

        sysEmp.setSex("true".equals(sysEmp.getSex()) ? "男" : "女");
        empService.save(sysEmp);

        sysUser.setEid(sysEmp.getId());
        sysUser.setLogintime(LocalDateTime.now().toString());

        userService.save(sysUser);

        return Result.success("success");
    }
}
