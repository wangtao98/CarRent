package com.iwiller.sys.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.iwiller.sys.constants.SysConstants;
import com.iwiller.sys.domain.SysMenu;
import com.iwiller.sys.domain.SysRole;
import com.iwiller.sys.mapper.SysMenuMapper;
import com.iwiller.sys.mapper.SysRoleMapper;
import com.iwiller.sys.service.ISysRoleService;
import com.iwiller.sys.utils.DataGridView;
import com.iwiller.sys.utils.TreeNode;
import com.iwiller.sys.vo.SysRoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysRoleServiceImpl implements ISysRoleService {
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public List<SysRole> queryAllRoleForList(SysRoleVo sysRoleVo) {
        return sysRoleMapper.queryAllRole(sysRoleVo);
    }

    /**
     * 后期权限管理完成之后再来改
     */
    @Override
    public List<SysRole> queryRoleByUserIdForList(SysRoleVo sysRoleVo, Integer userId) {
        return sysRoleMapper.queryAllRole(sysRoleVo);
    }


    /**
     * 后期权限管理完成之后再来修改
     * @param sysRoleVo
     * @return
     */
    @Override
    public DataGridView queryAllRole(SysRoleVo sysRoleVo) {
        Page<Object> page = PageHelper.startPage(sysRoleVo.getPage(),sysRoleVo.getLimit());
        List<SysRole> data = this.sysRoleMapper.queryAllRole(sysRoleVo);
        return new DataGridView(page.getTotal(),data);
    }

    @Override
    public void addRole(SysRoleVo sysRoleVo) {
        this.sysRoleMapper.insertSelective(sysRoleVo);
    }

    @Override
    public void updateRole(SysRoleVo sysRoleVo) {
        this.sysRoleMapper.updateByPrimaryKeySelective(sysRoleVo);
    }


    @Override
    public void deleteRole(Integer roleId) {
        //删除选中角色的数据
        this.sysRoleMapper.deleteByPrimaryKey(roleId);

        //根据角色id删除sys_role_menu里面的数据
        this.sysRoleMapper.deleteRoleMenuByRid(roleId);

        //根据角色id删除sys_role_user里面的数据
        this.sysRoleMapper.deleteRoleUserByRid(roleId);
    }

    @Override
    public void deleteBatchRole(Integer[] ids) {
        for (Integer roleId:
             ids) {
            deleteRole(roleId);
        }
    }

    @Override
    public DataGridView initRoleMenuTreeJson(Integer roleid) {
        //查询所有可用的菜单
        SysMenu menu = new SysMenu();
        menu.setAvailable(SysConstants.AVAILABLE_TRUE);
        List<SysMenu> allMenu = sysMenuMapper.queryAllMenu(menu);
        //根据角色ID查询当前角色拥有的菜单
        List<SysMenu> roleMenu = sysMenuMapper.queryMenuByRoleId(SysConstants.AVAILABLE_TRUE,roleid);

        List<TreeNode> data = new ArrayList<>();
        for (SysMenu m1 :
                allMenu) {
            String checkArr = SysConstants.CODE_ZERO+"";
            for (SysMenu m2:
                 roleMenu) {
                if(m1.getId()==m2.getId()){
                    checkArr = SysConstants.CODE_ONE+"";
                    break;
                }
            }
            Integer id = m1.getId();
            Integer pid = m1.getPid();
            String title = m1.getTitle();
            Boolean spread = m1.getSpread() == SysConstants.SPREAD_TRUE ? true : false;
            data.add(new TreeNode(id,pid,title,spread,checkArr));

        }
        
        return new DataGridView(data);
    }

    @Override
    public void saveRoleMenu(SysRoleVo sysRoleVo) {
        Integer rid = sysRoleVo.getRoleid();
        Integer[] mids = sysRoleVo.getIds();
        //根据角色id删除sys_role_menu里面的数据
        this.sysRoleMapper.deleteRoleMenuByRid(rid);
        //保存角色和菜单的关系
        for (Integer mid:
             mids) {
            this.sysRoleMapper.insertRoleMenu(rid,mid);
        }

    }


}
