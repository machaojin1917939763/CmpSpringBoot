package com.machaojin.datatanobject;

import com.machaojin.domain.SysUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author 马超金
 * @version 1.0
 * @date 2022/6/14 20:44
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class UserEmpDto extends SysUser {
    private Integer id;
    private String name;
    private String sex;
    private String dept;
    private Date hiredate;

    @Override
    public String toString() {
        return "UserEmpDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", dept='" + dept + '\'' +
                ", hiredate=" + hiredate +
                super.toString() +
                '}';
    }
}
