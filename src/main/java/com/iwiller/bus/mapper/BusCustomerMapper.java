package com.iwiller.bus.mapper;

import com.iwiller.bus.domain.BusCustomer;
import java.util.List;

public interface BusCustomerMapper {
    int deleteByPrimaryKey(String identity);

    int insert(BusCustomer record);

    int insertSelective(BusCustomer record);

    BusCustomer selectByPrimaryKey(String identity);

    List<BusCustomer> selectAll();

    int updateByPrimaryKey(BusCustomer record);

    int updateByPrimaryKeySelective(BusCustomer record);

    List<BusCustomer> queryAllCustomer(BusCustomer record);
}