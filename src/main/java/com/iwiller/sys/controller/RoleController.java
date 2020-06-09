package com.iwiller.sys.controller;

import com.iwiller.sys.service.ISysRoleService;
import com.iwiller.sys.utils.*;
import com.iwiller.sys.vo.SysRoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("role")
public class RoleController {

    @Autowired
    private ISysRoleService sysRoleService;

    /**
     * 加载菜单列表返回DataGridView
     * @param sysRoleVo
     * @return
     */
    @RequestMapping("loadAllRole")
    public DataGridView loadAllRole(SysRoleVo sysRoleVo){
        return this.sysRoleService.queryAllRole(sysRoleVo);
    }


    /**
     * 添加菜单
     * @param sysRoleVo
     * @return
     */
    @RequestMapping("addRole")
    public ResultObj addRole(SysRoleVo sysRoleVo){
        try{
            this.sysRoleService.addRole(sysRoleVo);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 更新菜单
     * @param sysRoleVo
     * @return
     */
    @RequestMapping("updateRole")
    public ResultObj updateRole(SysRoleVo sysRoleVo){
        try{
            this.sysRoleService.updateRole(sysRoleVo);
            return ResultObj.UPDATE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }


    /**
     * 删除菜单
     * @param sysRoleVo
     * @return
     */
    @RequestMapping("deleteRole")
    public ResultObj deleteRole(SysRoleVo sysRoleVo){
        try{
            this.sysRoleService.deleteRole(sysRoleVo.getRoleid());
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 批量删除角色
     */
    @RequestMapping("deleteBatchRole")
    public ResultObj deleteBatchRole(SysRoleVo sysRoleVo) {
        try {
            this.sysRoleService.deleteBatchRole(sysRoleVo.getIds());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    @RequestMapping("initRoleMenuTreeJson")
    public DataGridView initRoleMenuTreeJson(Integer roleid){
        return this.sysRoleService.initRoleMenuTreeJson(roleid);
    }

    @RequestMapping("saveRoleMenu")
    public ResultObj saveRoleMenu(SysRoleVo sysRoleVo) {
        try {
            this.sysRoleService.saveRoleMenu(sysRoleVo);
            return ResultObj.DISPATCH_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DISPATCH_ERROR;
        }
    }
}
