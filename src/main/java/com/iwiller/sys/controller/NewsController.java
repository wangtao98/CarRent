package com.iwiller.sys.controller;

import com.iwiller.sys.domain.SysNews;
import com.iwiller.sys.domain.SysUser;
import com.iwiller.sys.service.ISysNewsService;
import com.iwiller.sys.utils.DataGridView;
import com.iwiller.sys.utils.ResultObj;
import com.iwiller.sys.utils.WebUtils;
import com.iwiller.sys.vo.SysNewsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;


@RestController
@RequestMapping("news")
public class NewsController {

    @Autowired
    private ISysNewsService sysNewsService;

    /**
     * 加载公告列表返回DataGridView
     * @param sysNewsVo
     * @return
     */
    @RequestMapping("loadAllNews")
    public DataGridView loadAllNews(SysNewsVo sysNewsVo){
        return this.sysNewsService.queryAllNews(sysNewsVo);
    }

    /**
     * 添加公告
     * @param sysNewsVo
     * @return
     */
    @RequestMapping("addNews")
    public ResultObj addNews(SysNewsVo sysNewsVo){
        try{
            sysNewsVo.setCreatetime(new Date());
            SysUser sysUser = (SysUser) WebUtils.getHttpSession().getAttribute("user");
            sysNewsVo.setOpername(sysUser.getRealname());
            this.sysNewsService.addNews(sysNewsVo);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }



    @RequestMapping("updateNews")
    public ResultObj updateNews(SysNewsVo sysNewsVo){
        try{
            this.sysNewsService.updateNews(sysNewsVo);
            return ResultObj.UPDATE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 删除公告
     * @param sysNewsVo
     * @return
     */
    @RequestMapping("deleteNews")
    public ResultObj deleteNews(SysNewsVo sysNewsVo){
        try{
            this.sysNewsService.deleteNews(sysNewsVo.getId());
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 批量删除角色
     */
    @RequestMapping("deleteBatchNews")
    public ResultObj deleteBatchNews(SysNewsVo sysNewsVo) {
        try {
            this.sysNewsService.deleteBatchNews(sysNewsVo.getIds());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    @RequestMapping("loadNewsById")
    public SysNews loadNewsById(Integer id){
        return this.sysNewsService.queryNewsById(id);
    }
}
