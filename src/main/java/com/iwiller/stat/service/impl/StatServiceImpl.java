package com.iwiller.stat.service.impl;

import com.iwiller.stat.domain.BaseEntity;
import com.iwiller.stat.mapper.StatMapper;
import com.iwiller.stat.service.IStatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StatServiceImpl implements IStatService{
    @Autowired
    private StatMapper statMapper;

    @Override
    public List<BaseEntity> loadCustomerAreaStatList() {
        return this.statMapper.queryCustomerAreaStat();
    }

    @Override
    public List<BaseEntity> loadOpernameYearGradeStatList(String year) {
        return this.statMapper.queryOpernameYearGradeStat(year);
    }

    @Override
    public List<Double> loadCompanyYearGradeStat(String year) {
        return this.statMapper.queryCompanyYearGradeStat(year);
    }
}
