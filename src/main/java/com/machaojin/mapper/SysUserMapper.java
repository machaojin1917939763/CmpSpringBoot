package com.machaojin.mapper;

import com.machaojin.domain.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author machaojin
* @description 针对表【sys_user】的数据库操作Mapper
* @createDate 2022-06-22 16:02:18
* @Entity com.machaojin.domain.SysUser
*/
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

}




