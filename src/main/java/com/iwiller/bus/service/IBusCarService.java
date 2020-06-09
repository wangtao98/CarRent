package com.iwiller.bus.service;

import com.iwiller.bus.domain.BusCar;
import com.iwiller.bus.vo.BusCarVo;
import com.iwiller.sys.utils.DataGridView;

public interface IBusCarService {

    /**
     * 查询所有日志
     * @param busCarVo
     * @return
     */
    public DataGridView queryAllCar(BusCarVo busCarVo);

    /**
     * 添加日志
     * @param busCarVo
     */
    public void addCar(BusCarVo busCarVo);

    /**
     * 修改日志
     * @param busCarVo
     */
    public void updateCar(BusCarVo busCarVo);


    /**
     * 根据id删除日志
     * @param carnumber
     */
    public void deleteCar(String carnumber);

    /**
     * 批量删除日志
     * @param carnumbers
     */
    public void deleteBatchCar(String[] carnumbers);

    BusCar queryCarByCarNumber(String carnumber);
}
