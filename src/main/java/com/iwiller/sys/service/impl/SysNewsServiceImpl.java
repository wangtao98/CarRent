package com.iwiller.sys.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.iwiller.sys.domain.SysNews;
import com.iwiller.sys.mapper.SysNewsMapper;
import com.iwiller.sys.service.ISysNewsService;
import com.iwiller.sys.utils.DataGridView;
import com.iwiller.sys.vo.SysNewsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysNewsServiceImpl implements ISysNewsService {
    @Autowired
    private SysNewsMapper sysNewsMapper;

    @Override
    public DataGridView queryAllNews(SysNewsVo sysNewsVo) {
        Page<Object> page = PageHelper.startPage(sysNewsVo.getPage(),sysNewsVo.getLimit());
        List<SysNews> data = this.sysNewsMapper.queryAllNews(sysNewsVo);
        return new DataGridView(page.getTotal(),data);
    }

    @Override
    public void addNews(SysNewsVo sysNewsVo) {
        this.sysNewsMapper.insertSelective(sysNewsVo);
    }

    @Override
    public void updateNews(SysNewsVo sysNewsVo) {
        this.sysNewsMapper.updateByPrimaryKeySelective(sysNewsVo);
    }

    @Override
    public void deleteNews(Integer id) {
        this.sysNewsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteBatchNews(Integer[] ids) {
        for (Integer id :
                ids) {
            deleteNews(id);
        }
    }

    @Override
    public SysNews queryNewsById(Integer id) {
        return this.sysNewsMapper.selectByPrimaryKey(id);
    }


}
