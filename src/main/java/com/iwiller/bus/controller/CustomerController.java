package com.iwiller.bus.controller;

import com.iwiller.bus.service.IBusCustomerService;
import com.iwiller.bus.vo.BusCustomerVo;
import com.iwiller.sys.utils.DataGridView;
import com.iwiller.sys.utils.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    private IBusCustomerService busCustomerService;

    /**
     * 加载菜单列表返回DataGridView
     * @param busCustomerVo
     * @return
     */
    @RequestMapping("loadAllCustomer")
    public DataGridView loadAllCustomer(BusCustomerVo busCustomerVo){
        return this.busCustomerService.queryAllCustomer(busCustomerVo);
    }

    /**
     * 添加客户
     * @param busCustomer
     * @return
     */
    @RequestMapping("addCustomer")
    public ResultObj addCustomer(BusCustomerVo busCustomer){
        try{
            busCustomer.setCreatetime(new Date());
            this.busCustomerService.addCustomer(busCustomer);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }



    @RequestMapping("updateCustomer")
    public ResultObj updateCustomer(BusCustomerVo busCustomer){
        try{
            this.busCustomerService.updateCustomer(busCustomer);
            return ResultObj.UPDATE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }
    
    /**
     * 删除菜单
     * @param busCustomerVo
     * @return
     */
    @RequestMapping("deleteCustomer")
    public ResultObj deleteCustomer(BusCustomerVo busCustomerVo){
        try{
            this.busCustomerService.deleteCustomer(busCustomerVo.getIdentity());
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 批量删除角色
     */
    @RequestMapping("deleteBatchCustomer")
    public ResultObj deleteBatchCustomer(BusCustomerVo busCustomerVo) {
        try {
            this.busCustomerService.deleteBatchCustomer(busCustomerVo.getIds());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
}
