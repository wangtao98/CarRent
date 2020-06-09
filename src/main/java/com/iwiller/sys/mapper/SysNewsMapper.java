package com.iwiller.sys.mapper;

import com.iwiller.sys.domain.SysNews;
import java.util.List;

public interface SysNewsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysNews record);

    int insertSelective(SysNews record);

    SysNews selectByPrimaryKey(Integer id);

    List<SysNews> selectAll();

    int updateByPrimaryKey(SysNews record);

    int updateByPrimaryKeySelective(SysNews record);

    List<SysNews> queryAllNews(SysNews record);
}