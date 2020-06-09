package com.iwiller.sys.mapper;

import com.iwiller.sys.domain.SysMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysMenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysMenu record);

    int insertSelective(SysMenu record);

    SysMenu selectByPrimaryKey(Integer id);

    List<SysMenu> selectAll();

    int updateByPrimaryKey(SysMenu record);

    int updateByPrimaryKeySelective(SysMenu record);

    List<SysMenu> queryAllMenu(SysMenu sysMenu);

    /**
     * 根据pid查询菜单数量
     * @param pid
     * @return
     */
    Integer queryMenuByPid(@Param("pid") Integer pid);

    /**
     * 同时删除用户拥有该菜单的权利
     * 根据mid删除sys_role_menu里面的数据
     * @param mid
     */
    void deleteRoleMenuByMid(@Param("mid") Integer mid);

    /**
     * 根据角色ID查询当前角色拥有的菜单
     */
    List<SysMenu> queryMenuByRoleId(@Param("available") Integer available, @Param("rid") Integer rid);

    List<SysMenu> queryMenuByUid(@Param("available") Integer available, @Param("uid") Integer userId);
}