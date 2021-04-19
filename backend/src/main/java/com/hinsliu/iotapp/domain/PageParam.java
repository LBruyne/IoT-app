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

    public PageParam() {
        this.setOffset();
    }

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
        return offset;
    }

    public void setOffset() {
        if(getPage() > 0 && getPageSize() > 0) {
            this.offset = (page - 1) * pageSize;
        }
        else {
            this.offset = 0;
        }
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }
}
