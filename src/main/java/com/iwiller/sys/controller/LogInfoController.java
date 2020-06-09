package com.iwiller.sys.controller;

import com.iwiller.sys.service.ISysLogLoginService;
import com.iwiller.sys.utils.DataGridView;
import com.iwiller.sys.utils.ResultObj;
import com.iwiller.sys.vo.SysLogLoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("logInfo")
public class LogInfoController {

    @Autowired
    private ISysLogLoginService sysLogLoginService;

    /**
     * 加载菜单列表返回DataGridView
     * @param sysLogLoginVo
     * @return
     */
    @RequestMapping("loadAllLogInfo")
    public DataGridView loadAllLogInfo(SysLogLoginVo sysLogLoginVo){
        return this.sysLogLoginService.queryAllLogLogin(sysLogLoginVo);
    }
    
    /**
     * 删除菜单
     * @param sysLogLoginVo
     * @return
     */
    @RequestMapping("deleteLogInfo")
    public ResultObj deleteLogInfo(SysLogLoginVo sysLogLoginVo){
        try{
            this.sysLogLoginService.deleteLogLogin(sysLogLoginVo.getId());
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 批量删除角色
     */
    @RequestMapping("deleteBatchLogInfo")
    public ResultObj deleteBatchLogInfo(SysLogLoginVo sysLogLoginVo) {
        try {
            this.sysLogLoginService.deleteBatchLogLogin(sysLogLoginVo.getIds());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    
}
