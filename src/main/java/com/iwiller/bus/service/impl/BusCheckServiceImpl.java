package com.iwiller.bus.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.iwiller.bus.domain.BusCar;
import com.iwiller.bus.domain.BusCheck;
import com.iwiller.bus.domain.BusCustomer;
import com.iwiller.bus.domain.BusRent;
import com.iwiller.bus.mapper.BusCarMapper;
import com.iwiller.bus.mapper.BusCheckMapper;
import com.iwiller.bus.mapper.BusCustomerMapper;
import com.iwiller.bus.mapper.BusRentMapper;
import com.iwiller.bus.service.IBusCheckService;
import com.iwiller.bus.service.IBusRentService;
import com.iwiller.bus.vo.BusCheckVo;
import com.iwiller.sys.constants.SysConstants;
import com.iwiller.sys.domain.SysUser;
import com.iwiller.sys.utils.DataGridView;
import com.iwiller.sys.utils.RandomUtils;
import com.iwiller.sys.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BusCheckServiceImpl implements IBusCheckService {
    @Autowired
    private BusRentMapper rentMapper;
    @Autowired
    private BusCarMapper carMapper;
    @Autowired
    private BusCustomerMapper customerMapper;
    @Autowired
    private BusCheckMapper checkMapper;


    @Override
    public Map<String, Object> initCheckFormData(String rentid) {
        //查询出租单
        BusRent rent = rentMapper.selectByPrimaryKey(rentid);
        //查询客户
        BusCustomer customer = customerMapper.selectByPrimaryKey(rent.getIdentity());
        //查询车辆
        BusCar car = carMapper.selectByPrimaryKey(rent.getCarnumber());
        //组装Check
        BusCheck check = new BusCheck();
        check.setCheckid(RandomUtils.createRandomStringUseTime(SysConstants.CAR_ORDER_JC));
        check.setRentid(rentid);
        check.setCheckdate(new Date());
        SysUser user = (SysUser) WebUtils.getHttpSession().getAttribute("user");
        check.setOpername(user.getRealname());

        Map<String,Object> map = new HashMap<>();
        map.put("rent",rent);
        map.put("customer",customer);
        map.put("car",car);
        map.put("check",check);

        return map;
    }

    @Override
    public void addCheck(BusCheckVo checkVo) {
        this.checkMapper.insertSelective(checkVo);
        //更改出租单的状态
        BusRent busRent = this.rentMapper.selectByPrimaryKey(checkVo.getRentid());
        busRent.setRentflag(SysConstants.RENT_BACK_TRUE);
        this.rentMapper.updateByPrimaryKeySelective(busRent);
        //更改汽车状态
        BusCar car = this.carMapper.selectByPrimaryKey(busRent.getCarnumber());
        car.setIsrenting(SysConstants.RENT_CAR_FALSE);
        this.carMapper.updateByPrimaryKeySelective(car);


    }

    /**
     * 查询检查单
     * @param checkVo
     * @return
     */
    @Override
    public DataGridView queryAllCheck(BusCheckVo checkVo) {
        Page<BusCheck> page = PageHelper.startPage(checkVo.getPage(),checkVo.getLimit());
        List<BusCheck> data = this.checkMapper.queryAllCheck(checkVo);
        return new DataGridView(page.getTotal(),data);
    }

    @Override
    public void updateCheck(BusCheckVo checkVo) {
        this.checkMapper.updateByPrimaryKeySelective(checkVo);
    }
}
