package com.iwiller.bus.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.iwiller.bus.domain.BusCustomer;
import com.iwiller.bus.mapper.BusCustomerMapper;
import com.iwiller.bus.service.IBusCustomerService;
import com.iwiller.bus.vo.BusCustomerVo;
import com.iwiller.sys.utils.DataGridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusCustomerServiceImpl implements IBusCustomerService {
    @Autowired
    private BusCustomerMapper busCustomerMapper;

    @Override
    public DataGridView queryAllCustomer(BusCustomerVo busCustomerVo) {
        Page<Object> page = PageHelper.startPage(busCustomerVo.getPage(),busCustomerVo.getLimit());
        List<BusCustomer> data = this.busCustomerMapper.queryAllCustomer(busCustomerVo);
        return new DataGridView(page.getTotal(),data);
    }

    @Override
    public void addCustomer(BusCustomerVo busCustomerVo) {
        this.busCustomerMapper.insertSelective(busCustomerVo);
    }

    @Override
    public void updateCustomer(BusCustomerVo busCustomerVo) {
        this.busCustomerMapper.updateByPrimaryKeySelective(busCustomerVo);
    }

    @Override
    public void deleteCustomer(String identity) {
        this.busCustomerMapper.deleteByPrimaryKey(identity);
    }

    @Override
    public void deleteBatchCustomer(String[] identities) {
        for (String identity:
             identities) {
            deleteCustomer(identity);
        }
    }

    @Override
    public BusCustomer queryCustomerByIdentity(String identity) {
        return this.busCustomerMapper.selectByPrimaryKey(identity);
    }

    @Override
    public List<BusCustomer> queryAllCustomerForList(BusCustomerVo customerVo) {
        return this.busCustomerMapper.queryAllCustomer(customerVo);
    }

}
