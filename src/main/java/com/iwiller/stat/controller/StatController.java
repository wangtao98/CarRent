package com.iwiller.stat.controller;

import com.iwiller.bus.domain.BusCustomer;
import com.iwiller.bus.domain.BusRent;
import com.iwiller.bus.service.IBusCustomerService;
import com.iwiller.bus.service.IBusRentService;
import com.iwiller.bus.vo.BusCustomerVo;
import com.iwiller.stat.domain.BaseEntity;
import com.iwiller.stat.service.IStatService;
import com.iwiller.stat.utils.ExportCustomerUtils;
import com.iwiller.stat.utils.ExportRentUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("stat")
@Controller
public class StatController {

    @Autowired
    private IStatService statService;
    @Autowired
    private IBusCustomerService customerService;
    @Autowired
    private IBusRentService rentService;


    /**
     * 跳转客户地区统计页面
     * @return
     */
    @RequestMapping("toCustomerAreaStat")
    public String toCustomerAreaStat(){
        return "stat/customerAreaStat";
    }

    /**
     * 加载客户地区数据
     * @return
     */
    @RequestMapping("loadCustomerAreaStatJson")
    @ResponseBody
    public List<BaseEntity> loadCustomerAreaStatJson(){
        return this.statService.loadCustomerAreaStatList();
    }

    /**
     * 跳转到业务员年度统计页面
     * @return
     */
    @RequestMapping("toOpernameYearGradeStat")
    public String toOpernameYearGradeStat(){
        return "stat/opernameYearGradeStat";
    }
    /**
     * 加载业务员年度统计数据
     */
    @RequestMapping("loadOpernameYearGradeStat")
    @ResponseBody
    public Map<String,Object> opernameYearGradeStat(String year){
        List<BaseEntity> entities = this.statService.loadOpernameYearGradeStatList(year);
        Map<String,Object> map = new HashMap<>();
        List<String> names = new ArrayList<>();
        List<Double> values = new ArrayList<>();
        for (BaseEntity entity :
                entities) {
            names.add(entity.getName());
            values.add(entity.getValue());
        }
        map.put("name",names);
        map.put("value",values);
        return map;

    }

    @RequestMapping("toCompanyYearGradeStat")
    public String toCompanyYearGradeStat(){
        return "stat/companyYearGradeStat";
    }

    @RequestMapping("loadCompanyYearGradeStat")
    @ResponseBody
    public List<Double> loadCompanyYearGradeStat(String year){
        List<Double> entities = this.statService.loadCompanyYearGradeStat(year);
        for (int i = 0; i < entities.size(); i++) {
            if(null==entities.get(i)){
                entities.set(i,0.0);
            }
        }
        return entities;
    }

    /**
     * 导出客户数据
     * @param customerVo
     * @param response
     * @return
     */
    @RequestMapping("exportCustomer")
    public ResponseEntity<Object> exportCustomer(BusCustomerVo customerVo, HttpServletResponse response){
        List<BusCustomer> customers = customerService.queryAllCustomerForList(customerVo);
        String fileName = "客户数据.xls";
        String sheetName = "客户数据";//表名
        ByteArrayOutputStream bos = ExportCustomerUtils.exportCustomer(customers,sheetName);

        try{
            fileName = URLEncoder.encode(fileName,"utf-8");
            //创建封装响应头信息的对象
            HttpHeaders header = new HttpHeaders();
            //封装响应内容类型(APPLICATION_OCTET_STREAM 响应的内容不限定)
            header.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            //设置下载的文件的名称
            header.setContentDispositionFormData("attachment",fileName);
            return new ResponseEntity<Object>(bos.toByteArray(),header, HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    @RequestMapping("exportRent")
    public ResponseEntity<Object> exportRent(String rentid){
        //根据出租单号查询出租单信息
        BusRent rent = rentService.queryRentByRentId(rentid);
        //根据身份证号查询客户信息
        BusCustomer customer = customerService.queryCustomerByIdentity(rent.getIdentity());

        String fileName = customer.getCustname()+"-的出租单.xls";
        String sheetName = customer.getCustname()+"出租单";
        ByteArrayOutputStream bos = ExportRentUtils.exportRent(rent,customer,sheetName);

        try{
            fileName = URLEncoder.encode(fileName,"UTF-8");
            //创建封装响应头的信息
            HttpHeaders headers = new HttpHeaders();
            //封装响应内容类型(APPLICATION_OCTET_STREAM 响应的内容不限定)
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            //设置下载的文件名称
            headers.setContentDispositionFormData("attachment",fileName);
            return new ResponseEntity<Object>(bos.toByteArray(),headers,HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }



}
