package com.iwiller.bus.service;

import com.iwiller.bus.domain.BusRent;
import com.iwiller.bus.vo.BusRentVo;
import com.iwiller.sys.utils.DataGridView;


public interface IBusRentService {
    /**
     * 保存
     * @param busRentVo
     */
    void addRent(BusRentVo busRentVo);

    /**
     * 查询
     * @param busRentVo
     * @return
     */
    DataGridView queryAllRent(BusRentVo busRentVo);

    /**
     * 更新
     * @param busRentVo
     */
    void updateRent(BusRentVo busRentVo);

    /**
     * 删除
     * @param rentid
     */
    void deleteRent(String rentid);

    /**
     *
     * @param rentid
     * @return
     */
    BusRent queryRentByRentId(String rentid);
}
