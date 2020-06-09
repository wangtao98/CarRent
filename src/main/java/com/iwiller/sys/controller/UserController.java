package com.iwiller.sys.controller;

import com.iwiller.sys.service.ISysUserService;
import com.iwiller.sys.utils.DataGridView;
import com.iwiller.sys.utils.ResultObj;
import com.iwiller.sys.vo.SysUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private ISysUserService sysUserService;

    /**
     * 加载菜单列表返回DataGridView
     * @param sysUserVo
     * @return
     */
    @RequestMapping("loadAllUser")
    public DataGridView loadAllUser(SysUserVo sysUserVo){
        return this.sysUserService.queryAllUser(sysUserVo);
    }


    /**
     * 添加菜单
     * @param sysUserVo
     * @return
     */
    @RequestMapping("addUser")
    public ResultObj addUser(SysUserVo sysUserVo){
        try{
            this.sysUserService.addUser(sysUserVo);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 更新菜单
     * @param sysUserVo
     * @return
     */
    @RequestMapping("updateUser")
    public ResultObj updateUser(SysUserVo sysUserVo){
        try{
            this.sysUserService.updateUser(sysUserVo);
            return ResultObj.UPDATE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }


    /**
     * 删除菜单
     * @param sysUserVo
     * @return
     */
    @RequestMapping("deleteUser")
    public ResultObj deleteUser(SysUserVo sysUserVo){
        try{
            this.sysUserService.deleteUser(sysUserVo.getUserid());
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 批量删除角色
     */
    @RequestMapping("deleteBatchUser")
    public ResultObj deleteBatchUser(SysUserVo sysUserVo) {
        try {
            this.sysUserService.deleteBatchUser(sysUserVo.getIds());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 重置用户密码
     */
    @RequestMapping("resetUserPwd")
    public ResultObj resetUserPwd(SysUserVo sysUserVo) {
        try {
            this.sysUserService.resetUserPwd(sysUserVo.getUserid());
            return ResultObj.RESET_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.RESET_ERROR;
        }
    }

    /**
     * 加载用户管理的分配角色的数据
     * @param sysUserVo
     * @return
     */
    @RequestMapping("initUserRole")
    public DataGridView initUserRole(SysUserVo sysUserVo){
        return this.sysUserService.queryUserRole(sysUserVo.getUserid());
    }

    @RequestMapping("saveUserRole")
    public ResultObj saveUserRole(SysUserVo sysUserVo) {
        try {
            this.sysUserService.saveUserRole(sysUserVo);
            return ResultObj.DISPATCH_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DISPATCH_ERROR;
        }
    }

}
