package com.iwiller.sys.service;

import com.iwiller.sys.domain.SysMenu;
import com.iwiller.sys.utils.DataGridView;
import com.iwiller.sys.vo.SysMenuVo;

import java.util.List;

/**
 * 菜单管理的服务接口
 */
public interface ISysMenuService {
    /**
     *查询所有菜单返回
     * @param sysMenuVo
     * @return
     */
    public List<SysMenu> queryAllMenuForList(SysMenuVo sysMenuVo);

    /**
     * 根据用户id查询相应的菜单
     * @param sysMenuVo
     * @return
     */
    public List<SysMenu> queryMenuByUserIdForList(SysMenuVo sysMenuVo,Integer userId);

    /**
     * 查询所有菜单
     * @param sysMenuVo
     * @return
     */
    public DataGridView queryAllMenu(SysMenuVo sysMenuVo);

    /**
     * 添加菜单
     * @param sysMenuVo
     */
    public void addMenu(SysMenuVo sysMenuVo);

    /**
     * 修改菜单
     * @param sysMenuVo
     */
    public void updateMenu(SysMenuVo sysMenuVo);

    /**
     * 根据pid查询菜单数量
     * @param id
     * @return
     */
    public Integer queryMenuByPid(Integer id);

    /**
     * 根据id删除菜单
     * @param sysMenuVo
     */
    public void deleteMenu(SysMenuVo sysMenuVo);

}
