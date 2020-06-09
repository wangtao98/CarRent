package com.iwiller.sys.service;

import com.iwiller.sys.domain.SysUser;
import com.iwiller.sys.domain.SysUser;
import com.iwiller.sys.utils.DataGridView;
import com.iwiller.sys.vo.SysUserVo;
import com.iwiller.sys.vo.SysUserVo;

import java.util.List;

public interface ISysUserService {

    SysUser login(SysUserVo sysUserVo);


    /**
     * 查询所有菜单
     * @param sysUserVo
     * @return
     */
    public DataGridView queryAllUser(SysUserVo sysUserVo);

    /**
     * 添加菜单
     * @param sysUserVo
     */
    public void addUser(SysUserVo sysUserVo);

    /**
     * 修改菜单
     * @param sysUserVo
     */
    public void updateUser(SysUserVo sysUserVo);


    /**
     * 根据id删除菜单
     * @param userid
     */
    public void deleteUser(Integer userid);

    /**
     * 批量删除菜单
     * @param ids
     */
    public void deleteBatchUser(Integer[] ids);

    public void resetUserPwd(Integer userid);

    DataGridView queryUserRole(Integer userid);

    void saveUserRole(SysUserVo sysUserVo);
}
