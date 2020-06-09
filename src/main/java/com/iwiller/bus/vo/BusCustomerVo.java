package com.iwiller.bus.vo;

import com.iwiller.bus.domain.BusCustomer;

public class BusCustomerVo extends BusCustomer {

    private Integer page;
    private Integer limit;
    private String[] ids;

    public String[] getIds() {
        return ids;
    }

    public void setIds(String[] ids) {
        this.ids = ids;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
