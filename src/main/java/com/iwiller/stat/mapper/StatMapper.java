package com.iwiller.stat.mapper;

import com.iwiller.stat.domain.BaseEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StatMapper {

    List<BaseEntity> queryCustomerAreaStat();

    List<BaseEntity> queryOpernameYearGradeStat(@Param("year") String year);

    List<Double> queryCompanyYearGradeStat(String year);
}
