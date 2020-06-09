package com.iwiller.sys.mapper;

import com.iwiller.sys.domain.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserMapper {
    int deleteByPrimaryKey(Integer userid);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Integer userid);

    List<SysUser> selectAll();

    int updateByPrimaryKey(SysUser record);

    int updateByPrimaryKeySelective(SysUser record);

    //登录
    SysUser login(SysUser sysUser);

    List<SysUser> queryAllUser(SysUser record);

    void insertUserRole(@Param("uid") Integer userid, @Param("rid") Integer rid);
}