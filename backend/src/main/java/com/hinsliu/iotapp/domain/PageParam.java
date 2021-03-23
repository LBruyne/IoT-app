package com.hinsliu.iotapp.domain;

import java.io.Serializable;

/**
 * @Description: When RPC involves a list of data, the request should contain page and pageSize parameters.
 * @author: liuxuanming
 * @date: 2021/03/23 3:03 下午
 */
public class PageParam implements Serializable {

    private Integer page;

    private Integer pageSize;

    private Integer offset;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getOffset() {
        if(getPage() == null || getPageSize() == null) return null;
        return (getPage() - 1) * getPageSize();
    }

}
