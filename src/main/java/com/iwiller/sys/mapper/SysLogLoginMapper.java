package com.iwiller.sys.mapper;

import com.iwiller.sys.domain.SysLogLogin;
import java.util.List;

public interface SysLogLoginMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysLogLogin record);

    int insertSelective(SysLogLogin record);

    SysLogLogin selectByPrimaryKey(Integer id);

    List<SysLogLogin> selectAll();

    int updateByPrimaryKey(SysLogLogin record);

    int updateByPrimaryKeySelective(SysLogLogin record);

    List<SysLogLogin> queryAllLogLogin(SysLogLogin record);
}