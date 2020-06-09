package com.iwiller.stat.service;

import com.iwiller.stat.domain.BaseEntity;

import java.util.List;

public interface IStatService {
    /**
     * 统计客户地区数据
     * @return
     */
    List<BaseEntity> loadCustomerAreaStatList();

    /**
     * 统计操作员业绩数据
     * @param year
     * @return
     */
    List<BaseEntity> loadOpernameYearGradeStatList(String year);

    /**
     *统计公司年收入数据
     * @param year
     * @return
     */
    List<Double> loadCompanyYearGradeStat(String year);
}
