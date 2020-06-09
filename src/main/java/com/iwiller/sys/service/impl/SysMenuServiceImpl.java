package com.iwiller.sys.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.iwiller.sys.domain.SysMenu;
import com.iwiller.sys.mapper.SysMenuMapper;
import com.iwiller.sys.service.ISysMenuService;
import com.iwiller.sys.utils.DataGridView;
import com.iwiller.sys.vo.SysMenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SysMenuServiceImpl implements ISysMenuService {
    @Autowired
    private SysMenuMapper sysMenuMapper;


    @Override
    public List<SysMenu> queryAllMenuForList(SysMenuVo sysMenuVo) {
        return sysMenuMapper.queryAllMenu(sysMenuVo);
    }

    /**
     * 后期权限管理完成之后再来改
     */
    @Override
    public List<SysMenu> queryMenuByUserIdForList(SysMenuVo sysMenuVo, Integer userId) {
        return sysMenuMapper.queryMenuByUid(sysMenuVo.getAvailable(),userId);
    }


    /**
     * 后期权限管理完成之后再来修改
     * @param sysMenuVo
     * @return
     */
    @Override
    public DataGridView queryAllMenu(SysMenuVo sysMenuVo) {
        Page<Object> page = PageHelper.startPage(sysMenuVo.getPage(),sysMenuVo.getLimit());
        List<SysMenu> data = this.sysMenuMapper.queryAllMenu(sysMenuVo);
        return new DataGridView(page.getTotal(),data);
    }

    @Override
    public void addMenu(SysMenuVo sysMenuVo) {
        this.sysMenuMapper.insertSelective(sysMenuVo);
    }

    @Override
    public void updateMenu(SysMenuVo sysMenuVo) {
        this.sysMenuMapper.updateByPrimaryKeySelective(sysMenuVo);
    }

    /**
     * 根据pid查询菜单数量
     * @param pid
     * @return
     */
    @Override
    public Integer queryMenuByPid(Integer pid) {
        return this.sysMenuMapper.queryMenuByPid(pid);
    }

    @Override
    public void deleteMenu(SysMenuVo sysMenuVo) {
        //删除选中菜单的数据
        this.sysMenuMapper.deleteByPrimaryKey(sysMenuVo.getId());

        //根据菜单id删除sys_role_menu里面的数据
        this.sysMenuMapper.deleteRoleMenuByMid(sysMenuVo.getId());


    }


}
