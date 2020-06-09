package com.iwiller.bus.service;

import com.iwiller.bus.domain.BusCheck;
import com.iwiller.bus.vo.BusCheckVo;
import com.iwiller.sys.utils.DataGridView;

import java.util.Map;

public interface IBusCheckService {

    Map<String, Object> initCheckFormData(String rentid);

    void addCheck(BusCheckVo checkVo);

    DataGridView queryAllCheck(BusCheckVo checkVo);

    void updateCheck(BusCheckVo checkVo);
}

