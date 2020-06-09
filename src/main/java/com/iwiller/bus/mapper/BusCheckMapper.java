package com.iwiller.bus.mapper;

import com.iwiller.bus.domain.BusCar;
import com.iwiller.bus.domain.BusCheck;
import java.util.List;

public interface BusCheckMapper {
    int deleteByPrimaryKey(String checkid);

    int insert(BusCheck record);

    int insertSelective(BusCheck record);

    BusCheck selectByPrimaryKey(String checkid);

    List<BusCheck> selectAll();

    int updateByPrimaryKey(BusCheck record);

    int updateByPrimaryKeySelective(BusCheck record);

    List<BusCheck> queryAllCheck(BusCheck busCheck);

}