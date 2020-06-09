package com.iwiller.bus.controller;

import com.iwiller.bus.domain.BusCar;
import com.iwiller.bus.service.IBusCarService;
import com.iwiller.bus.vo.BusCarVo;
import com.iwiller.sys.constants.SysConstants;
import com.iwiller.sys.utils.AppFileUtils;
import com.iwiller.sys.utils.DataGridView;
import com.iwiller.sys.utils.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("car")
public class CarController {

    @Autowired
    private IBusCarService busCarService;

    /**
     * 加载菜单列表返回DataGridView
     * @param busCarVo
     * @return
     */
    @RequestMapping("loadAllCar")
    public DataGridView loadAllCar(BusCarVo busCarVo){
        return this.busCarService.queryAllCar(busCarVo);
    }

    /**
     * 添加客户
     * @param busCar
     * @return
     */
    @RequestMapping("addCar")
    public ResultObj addCar(BusCarVo busCar){
        try{
            busCar.setCreatetime(new Date());
            //如果不是默认图片就去掉图片的_temp的后缀
            if(!busCar.getCarimg().equals(SysConstants.DEFAULT_CAR_IMG)) {
                String filePath= AppFileUtils.updateFileName(busCar.getCarimg(),SysConstants.FILE_UPLOAD_TEMP);
                busCar.setCarimg(filePath);
            }
            this.busCarService.addCar(busCar);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }



    @RequestMapping("updateCar")
    public ResultObj updateCar(BusCarVo busCar){
        try{
            String carimg = busCar.getCarimg();
            if(carimg.endsWith(SysConstants.FILE_UPLOAD_TEMP)){
                String filePath = AppFileUtils.updateFileName(busCar.getCarimg(),SysConstants.FILE_UPLOAD_TEMP);
                busCar.setCarimg(filePath);
                //把原来的删除
                BusCar car = this.busCarService.queryCarByCarNumber(busCar.getCarnumber());
                AppFileUtils.removeFileByPath(car.getCarimg());
            }
            this.busCarService.updateCar(busCar);
            return ResultObj.UPDATE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }
    
    /**
     * 删除菜单
     * @param busCarVo
     * @return
     */
    @RequestMapping("deleteCar")
    public ResultObj deleteCar(BusCarVo busCarVo){
        try{
            this.busCarService.deleteCar(busCarVo.getCarnumber());
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 批量删除角色
     */
    @RequestMapping("deleteBatchCar")
    public ResultObj deleteBatchCar(BusCarVo busCarVo) {
        try {
            this.busCarService.deleteBatchCar(busCarVo.getIds());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
}
