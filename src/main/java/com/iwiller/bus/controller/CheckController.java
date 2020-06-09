package com.iwiller.bus.controller;

import com.iwiller.bus.domain.BusRent;
import com.iwiller.bus.service.IBusCheckService;
import com.iwiller.bus.service.IBusRentService;
import com.iwiller.bus.vo.BusCheckVo;
import com.iwiller.sys.utils.DataGridView;
import com.iwiller.sys.utils.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("check")
public class CheckController {

    @Autowired
    private IBusRentService busRentService;
    @Autowired
    private IBusCheckService busCheckService;

    @RequestMapping("checkRentExist")
    public BusRent checkRentExist(String rentid){
        BusRent busRent = busRentService.queryRentByRentId(rentid);
        return busRent;
    }

    @RequestMapping("initCheckFormData")
    public Map<String,Object> initCheckFormData(String rentid){
        return this.busCheckService.initCheckFormData(rentid);
    }

    @RequestMapping("saveCheck")
    public ResultObj saveCheck(BusCheckVo checkVo){
        try{
            checkVo.setCreatetime(new Date());
            this.busCheckService.addCheck(checkVo);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    @RequestMapping("loadAllCheck")
    public DataGridView loadAllCheck(BusCheckVo checkVo){
        return this.busCheckService.queryAllCheck(checkVo);
    }

    @RequestMapping("updateCheck")
    public ResultObj updateCheck(BusCheckVo checkVo){
        try{
            this.busCheckService.updateCheck(checkVo);
            return ResultObj.UPDATE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

}
