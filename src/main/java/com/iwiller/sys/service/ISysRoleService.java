package com.iwiller.sys.service;

import com.iwiller.sys.domain.SysRole;
import com.iwiller.sys.utils.DataGridView;
import com.iwiller.sys.vo.SysRoleVo;

import java.util.List;

/**
 * 菜单管理的服务接口
 */
public interface ISysRoleService {
    /**
     *查询所有菜单返回
     * @param sysRoleVo
     * @return
     */
    public List<SysRole> queryAllRoleForList(SysRoleVo sysRoleVo);

    /**
     * 根据用户id查询相应的菜单
     * @param sysRoleVo
     * @return
     */
    public List<SysRole> queryRoleByUserIdForList(SysRoleVo sysRoleVo, Integer userId);

    /**
     * 查询所有菜单
     * @param sysRoleVo
     * @return
     */
    public DataGridView queryAllRole(SysRoleVo sysRoleVo);

    /**
     * 添加菜单
     * @param sysRoleVo
     */
    public void addRole(SysRoleVo sysRoleVo);

    /**
     * 修改菜单
     * @param sysRoleVo
     */
    public void updateRole(SysRoleVo sysRoleVo);


    /**
     * 根据id删除菜单
     * @param roleId
     */
    public void deleteRole(Integer roleId);

    /**
     * 批量删除菜单
     * @param ids
     */
    public void deleteBatchRole(Integer[] ids);

    public DataGridView initRoleMenuTreeJson(Integer roleid);

    void saveRoleMenu(SysRoleVo sysRoleVo);
}
