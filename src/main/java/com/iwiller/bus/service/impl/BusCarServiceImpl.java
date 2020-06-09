package com.iwiller.bus.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.iwiller.bus.domain.BusCar;
import com.iwiller.bus.mapper.BusCarMapper;
import com.iwiller.bus.service.IBusCarService;
import com.iwiller.bus.vo.BusCarVo;
import com.iwiller.sys.constants.SysConstants;
import com.iwiller.sys.utils.AppFileUtils;
import com.iwiller.sys.utils.DataGridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusCarServiceImpl implements IBusCarService {
    @Autowired
    private BusCarMapper busCarMapper;

    @Override
    public DataGridView queryAllCar(BusCarVo busCarVo) {
        Page<Object> page = PageHelper.startPage(busCarVo.getPage(),busCarVo.getLimit());
        List<BusCar> data = this.busCarMapper.queryAllCar(busCarVo);
        return new DataGridView(page.getTotal(),data);
    }

    @Override
    public void addCar(BusCarVo busCarVo) {
        this.busCarMapper.insertSelective(busCarVo);
    }

    @Override
    public void updateCar(BusCarVo busCarVo) {
        this.busCarMapper.updateByPrimaryKeySelective(busCarVo);
    }

    @Override
    public void deleteCar(String carnumber) {
        //先删除图片
        BusCar car = this.busCarMapper.selectByPrimaryKey(carnumber);
        //如果不是默认图片，不用删除
        if(!car.getCarimg().equals(SysConstants.DEFAULT_CAR_IMG)){
            AppFileUtils.deleteFileUsePath(car.getCarimg());
        }
        this.busCarMapper.deleteByPrimaryKey(carnumber);
    }

    @Override
    public void deleteBatchCar(String[] carnumbers) {
        for (String carnumber:
                carnumbers) {
            deleteCar(carnumber);
        }
    }

    @Override
    public BusCar queryCarByCarNumber(String carnumber) {
        return this.busCarMapper.selectByPrimaryKey(carnumber);
    }

}
