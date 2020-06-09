package com.iwiller.sys.mapper;

import com.iwiller.sys.domain.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRoleMapper {
    int deleteByPrimaryKey(Integer roleid);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(Integer roleid);

    List<SysRole> selectAll();

    int updateByPrimaryKey(SysRole record);

    int updateByPrimaryKeySelective(SysRole record);

    List<SysRole> queryAllRole(SysRole record);

    //根据角色id删除sys_role_menu里面的数据
    void deleteRoleMenuByRid(@Param("rid") Integer roleId);

    //根据角色id删除sys_role_user里面的数据
    void deleteRoleUserByRid(@Param("rid") Integer roleId);

    void insertRoleMenu(@Param("rid") Integer rid, @Param("mid") Integer mid);

    List<SysRole> queryRoleByUid(@Param("available") Integer available,@Param("uid") Integer userid);

    void deleteRoleUserByUid(@Param("uid") Integer userid);
}