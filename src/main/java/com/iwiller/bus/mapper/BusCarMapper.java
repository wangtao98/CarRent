package com.iwiller.bus.mapper;

import com.iwiller.bus.domain.BusCar;
import java.util.List;

public interface BusCarMapper {
    int deleteByPrimaryKey(String carnumber);

    int insert(BusCar record);

    int insertSelective(BusCar record);

    BusCar selectByPrimaryKey(String carnumber);

    List<BusCar> selectAll();

    int updateByPrimaryKey(BusCar record);

    int updateByPrimaryKeySelective(BusCar record);

    List<BusCar> queryAllCar(BusCar record);
}