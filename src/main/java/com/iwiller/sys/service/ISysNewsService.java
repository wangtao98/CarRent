package com.iwiller.sys.service;

import com.iwiller.sys.domain.SysNews;
import com.iwiller.sys.utils.DataGridView;
import com.iwiller.sys.vo.SysNewsVo;

/**
 * 公告管理的服务接口
 */
public interface ISysNewsService {

    /**
     * 查询所有公告
     * @param sysNewsVo
     * @return
     */
    public DataGridView queryAllNews(SysNewsVo sysNewsVo);

    /**
     * 添加公告
     * @param sysNewsVo
     */
    public void addNews(SysNewsVo sysNewsVo);

    /**
     * 修改公告
     * @param sysNewsVo
     */
    public void updateNews(SysNewsVo sysNewsVo);


    /**
     * 根据id删除公告
     * @param NewsId
     */
    public void deleteNews(Integer NewsId);

    /**
     * 批量删除公告
     * @param ids
     */
    public void deleteBatchNews(Integer[] ids);

    SysNews queryNewsById(Integer id);
}
