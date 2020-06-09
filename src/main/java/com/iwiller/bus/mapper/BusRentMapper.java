package com.iwiller.bus.mapper;

import com.iwiller.bus.domain.BusRent;
import java.util.List;

public interface BusRentMapper {
    int deleteByPrimaryKey(String rentid);

    int insert(BusRent record);

    int insertSelective(BusRent record);

    BusRent selectByPrimaryKey(String rentid);

    List<BusRent> selectAll();

    int updateByPrimaryKey(BusRent record);

    int updateByPrimaryKeySelective(BusRent record);

    List<BusRent> queryAllRent(BusRent record);
}