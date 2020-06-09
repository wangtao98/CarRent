package com.iwiller.sys.service;

import com.iwiller.sys.domain.SysLogLogin;
import com.iwiller.sys.utils.DataGridView;
import com.iwiller.sys.vo.SysLogLoginVo;

import java.util.List;

/**
 * 日志管理的服务接口
 */
public interface ISysLogLoginService {

    /**
     * 查询所有日志
     * @param sysLogLoginVo
     * @return
     */
    public DataGridView queryAllLogLogin(SysLogLoginVo sysLogLoginVo);

    /**
     * 添加日志
     * @param sysLogLoginVo
     */
    public void addLogLogin(SysLogLoginVo sysLogLoginVo);

    /**
     * 修改日志
     * @param sysLogLoginVo
     */
    public void updateLogLogin(SysLogLoginVo sysLogLoginVo);


    /**
     * 根据id删除日志
     * @param LogLoginId
     */
    public void deleteLogLogin(Integer LogLoginId);

    /**
     * 批量删除日志
     * @param ids
     */
    public void deleteBatchLogLogin(Integer[] ids);

}
