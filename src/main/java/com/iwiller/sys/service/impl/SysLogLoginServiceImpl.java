package com.iwiller.sys.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.iwiller.sys.constants.SysConstants;
import com.iwiller.sys.domain.SysMenu;
import com.iwiller.sys.domain.SysLogLogin;
import com.iwiller.sys.mapper.SysMenuMapper;
import com.iwiller.sys.mapper.SysLogLoginMapper;
import com.iwiller.sys.service.ISysLogLoginService;
import com.iwiller.sys.utils.DataGridView;
import com.iwiller.sys.utils.TreeNode;
import com.iwiller.sys.vo.SysLogLoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysLogLoginServiceImpl implements ISysLogLoginService {
    @Autowired
    private SysLogLoginMapper sysLogLoginMapper;

    @Override
    public DataGridView queryAllLogLogin(SysLogLoginVo sysLogLoginVo) {
        Page<Object> page = PageHelper.startPage(sysLogLoginVo.getPage(), sysLogLoginVo.getLimit());
        List<SysLogLogin> data = this.sysLogLoginMapper.queryAllLogLogin(sysLogLoginVo);
        return new DataGridView(page.getTotal(), data);
    }

    @Override
    public void addLogLogin(SysLogLoginVo sysLogLoginVo) {
        this.sysLogLoginMapper.insertSelective(sysLogLoginVo);
    }

    @Override
    public void updateLogLogin(SysLogLoginVo sysLogLoginVo) {
        this.sysLogLoginMapper.updateByPrimaryKeySelective(sysLogLoginVo);
    }


    @Override
    public void deleteLogLogin(Integer id) {
        //删除选中角色的数据
        this.sysLogLoginMapper.deleteByPrimaryKey(id);

    }

    @Override
    public void deleteBatchLogLogin(Integer[] ids) {
        for (Integer id :
                ids) {
            deleteLogLogin(id);
        }
    }


}


