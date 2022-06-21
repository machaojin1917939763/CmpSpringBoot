package com.machaojin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.machaojin.domain.SysUser;
import com.machaojin.service.SysUserService;
import com.machaojin.mapper.SysUserMapper;
import org.springframework.stereotype.Service;

/**
* @author machaojin
* @description 针对表【sys_user】的数据库操作Service实现
* @createDate 2022-06-14 10:46:50
*/
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser>
    implements SysUserService{

}




