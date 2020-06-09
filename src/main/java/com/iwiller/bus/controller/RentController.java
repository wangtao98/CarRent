package com.iwiller.bus.controller;


import com.iwiller.bus.domain.BusCustomer;
import com.iwiller.bus.service.IBusCustomerService;
import com.iwiller.bus.service.IBusRentService;
import com.iwiller.bus.vo.BusRentVo;
import com.iwiller.sys.constants.SysConstants;
import com.iwiller.sys.domain.SysUser;
import com.iwiller.sys.utils.DataGridView;
import com.iwiller.sys.utils.RandomUtils;
import com.iwiller.sys.utils.ResultObj;
import com.iwiller.sys.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("rent")
public class RentController {
    @Autowired
    private IBusCustomerService busCustomerService;
    @Autowired
    private IBusRentService busRentService;

    @RequestMapping("checkCustomerExist")
    public ResultObj checkCustomerExist(BusRentVo busRentVo){
        BusCustomer customer = busCustomerService.queryCustomerByIdentity(busRentVo.getIdentity());
        if(null!=customer){
            return ResultObj.STATUS_TRUE;
        }else{
            return ResultObj.STATUS_FALSE;
        }
    }

    /**
     * 初始化添加出租单的表单数据
     * @param busRentVo
     * @return
     */
    @RequestMapping("initRentFrom")
    public BusRentVo initRentFrom(BusRentVo busRentVo){
        //生成出租单号
        busRentVo.setRentid(RandomUtils.createRandomStringUseTime(SysConstants.CAR_ORDER_CZ));
        //生成出租时间
        busRentVo.setBegindate(new Date());
        SysUser user = (SysUser) WebUtils.getHttpSession().getAttribute("user");
        //设置操作员
        busRentVo.setOpername(user.getRealname());
        return busRentVo;
    }


    /**
     * 保存出租单信息
     * @param busRentVo
     * @return
     */
    @RequestMapping("saveRent")
    public ResultObj saveRent(BusRentVo busRentVo){
        try{
            //设置创建时间
            busRentVo.setCreatetime(new Date());
            //设置归还状态
            busRentVo.setRentflag(SysConstants.RENT_BACK_FALSE);
            //保存
            busRentService.addRent(busRentVo);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }


    /*************出租单管理*************/
    /**
     * 查询出租单的数据
     * @param busRentVo
     * @return
     */
    @RequestMapping("loadAllRent")
    public DataGridView loadAllRent(BusRentVo busRentVo){
        return this.busRentService.queryAllRent(busRentVo);
    }

    /**
     * 更新出租单的数据
     * @param busRentVo
     * @return
     */
    @RequestMapping("updateRent")
    public ResultObj updateRent(BusRentVo busRentVo){
        try{
            //更新
            busRentService.updateRent(busRentVo);
            return ResultObj.UPDATE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 删除出租单的数据
     * @param busRentVo
     * @return
     */
    @RequestMapping("deleteRent")
    public ResultObj deleteRent(BusRentVo busRentVo){
        try{
            //删除
            busRentService.deleteRent(busRentVo.getRentid());
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }




}
