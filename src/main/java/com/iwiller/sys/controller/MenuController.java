package com.iwiller.sys.controller;

import com.iwiller.sys.constants.SysConstants;
import com.iwiller.sys.domain.SysMenu;
import com.iwiller.sys.domain.SysUser;
import com.iwiller.sys.service.ISysMenuService;
import com.iwiller.sys.utils.*;
import com.iwiller.sys.vo.SysMenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("menu")
public class MenuController {

    @Autowired
    private ISysMenuService sysMenuService;

    @RequestMapping("loadIndexLeftMenuJson")
    public List<TreeNode> loadIndexLeftMenuJson(SysMenuVo sysMenuVo){
        //得到当前登录的对象
        SysUser user = (SysUser) WebUtils.getHttpSession().getAttribute("user");
        List<SysMenu> list = null;
        sysMenuVo.setAvailable(SysConstants.AVAILABLE_TRUE);
        if(user.getType() == SysConstants.USER_TYPE_SUPER){
            list = sysMenuService.queryAllMenuForList(sysMenuVo);
        }else{
            list = sysMenuService.queryMenuByUserIdForList(sysMenuVo,user.getUserid());
        }

        List<TreeNode> nodes = new ArrayList<>();

        for (SysMenu menu :
                list) {
            Integer id = menu.getId();
            Integer pid = menu.getPid();
            String title = menu.getTitle();
            String icon = menu.getIcon();
            String href = menu.getHref();
            Boolean spread = menu.getSpread()==1?true:false;
            String target = menu.getTarget();
            nodes.add(new TreeNode(id,pid,title,icon,href,spread,target));
        }

        return TreeNodeBuilder.builder(nodes,1);

    }

    /**
     * 加载菜单管理左边的菜单树
     * @param sysMenuVo
     * @return
     */
    @RequestMapping("loadMenuManagerLeftTreeJson")
    public DataGridView loadMenuManagerLeftTreeJson(SysMenuVo sysMenuVo){
        sysMenuVo.setAvailable(SysConstants.AVAILABLE_TRUE);
        List<SysMenu> list = this.sysMenuService.queryAllMenuForList(sysMenuVo);
        List<TreeNode> nodes = new ArrayList<>();
        for (SysMenu menu :
                list) {
            Integer id = menu.getId();
            Integer pid = menu.getPid();
            String title = menu.getTitle();
            String icon = menu.getIcon();
            String href = menu.getHref();
            Boolean spread = menu.getSpread()==1?true:false;
            String target = menu.getTarget();
            nodes.add(new TreeNode(id,pid,title,icon,href,spread,target));
        }
        return new DataGridView(nodes);

    }

    /**
     * 加载菜单列表返回DataGridView
     * @param sysMenuVo
     * @return
     */
    @RequestMapping("loadAllMenu")
    public DataGridView loadAllMenu(SysMenuVo sysMenuVo){
        return this.sysMenuService.queryAllMenu(sysMenuVo);
    }


    /**
     * 添加菜单
     * @param sysMenuVo
     * @return
     */
    @RequestMapping("addMenu")
    public ResultObj addMenu(SysMenuVo sysMenuVo){
        try{
            this.sysMenuService.addMenu(sysMenuVo);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 更新菜单
     * @param sysMenuVo
     * @return
     */
    @RequestMapping("updateMenu")
    public ResultObj updateMenu(SysMenuVo sysMenuVo){
        try{
            this.sysMenuService.updateMenu(sysMenuVo);
            return ResultObj.UPDATE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 根据pid判断有没有子节点
     * 有的话code>=0
     * 没有code<0
     * @param sysMenuVo
     * @return
     */
    @RequestMapping("checkMenuHasChildren")
    public ResultObj checkMenuHasChildren(SysMenuVo sysMenuVo){
        Integer count = this.sysMenuService.queryMenuByPid(sysMenuVo.getId());
        if(count>0){
            return ResultObj.STATUS_TRUE;
        }else{
            return ResultObj.STATUS_FALSE;
        }
    }

    /**
     * 删除菜单
     * @param sysMenuVo
     * @return
     */
    @RequestMapping("deleteMenu")
    public ResultObj deleteMenu(SysMenuVo sysMenuVo){
        try{
            this.sysMenuService.deleteMenu(sysMenuVo);
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }


}
