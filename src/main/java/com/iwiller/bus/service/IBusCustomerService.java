package com.iwiller.bus.service;

import com.iwiller.bus.domain.BusCustomer;
import com.iwiller.bus.vo.BusCustomerVo;
import com.iwiller.sys.utils.DataGridView;

import java.util.List;

public interface IBusCustomerService {

    /**
     * 查询所有日志
     * @param busCustomerVo
     * @return
     */
    public DataGridView queryAllCustomer(BusCustomerVo busCustomerVo);

    /**
     * 添加日志
     * @param busCustomerVo
     */
    public void addCustomer(BusCustomerVo busCustomerVo);

    /**
     * 修改日志
     * @param busCustomerVo
     */
    public void updateCustomer(BusCustomerVo busCustomerVo);


    /**
     * 根据id删除日志
     * @param identity
     */
    public void deleteCustomer(String identity);

    /**
     * 批量删除日志
     * @param identities
     */
    public void deleteBatchCustomer(String[] identities);

    BusCustomer queryCustomerByIdentity(String identity);


    List<BusCustomer> queryAllCustomerForList(BusCustomerVo customerVo);
}
