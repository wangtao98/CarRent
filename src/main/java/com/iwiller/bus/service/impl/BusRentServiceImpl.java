package com.iwiller.bus.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.iwiller.bus.domain.BusCar;
import com.iwiller.bus.domain.BusRent;
import com.iwiller.bus.mapper.BusCarMapper;
import com.iwiller.bus.mapper.BusRentMapper;
import com.iwiller.bus.service.IBusRentService;
import com.iwiller.bus.vo.BusRentVo;
import com.iwiller.sys.constants.SysConstants;
import com.iwiller.sys.utils.DataGridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusRentServiceImpl implements IBusRentService {

    @Autowired
    private BusRentMapper rentMapper;
    @Autowired
    private BusCarMapper carMapper;


    @Override
    public void addRent(BusRentVo busRentVo) {
        this.rentMapper.insertSelective(busRentVo);
        //更改车辆的出租状态
        BusCar car = new BusCar();
        car.setIsrenting(SysConstants.RENT_CAR_TRUE);
        car.setCarnumber(busRentVo.getCarnumber());
        carMapper.updateByPrimaryKeySelective(car);

    }

    @Override
    public DataGridView queryAllRent(BusRentVo busRentVo) {
        Page<Object> page = PageHelper.startPage(busRentVo.getPage(),busRentVo.getLimit());
        List<BusRent> data = this.rentMapper.queryAllRent(busRentVo);
        return new DataGridView(page.getTotal(),data);
    }

    @Override
    public void updateRent(BusRentVo busRentVo) {
        this.rentMapper.updateByPrimaryKeySelective(busRentVo);
    }

    @Override
    public void deleteRent(String rentid) {
        //先改变出租汽车的状态
        BusRent rent = this.rentMapper.selectByPrimaryKey(rentid);
        BusCar car = new BusCar();
        car.setCarnumber(rent.getCarnumber());
        car.setIsrenting(SysConstants.RENT_CAR_FALSE);
        this.carMapper.updateByPrimaryKeySelective(car);
        //再删除出租单
        this.rentMapper.deleteByPrimaryKey(rentid);
    }

    @Override
    public BusRent queryRentByRentId(String rentid) {
        return this.rentMapper.selectByPrimaryKey(rentid);
    }


}
