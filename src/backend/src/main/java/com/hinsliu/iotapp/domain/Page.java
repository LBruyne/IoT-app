package com.hinsliu.iotapp.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @Description: Page to maintain several items.
 * @author: liuxuanming
 * @date: 2021/03/29 11:14 上午
 */
public class Page<T> implements Serializable {

    private Integer page;

    private Integer pageSize;

    private Integer count;

    private List<T> results;

    public Page(Integer page, Integer pageSize, Integer count, List<T> results){
        this.page = page;
        this.pageSize = pageSize;
        this.count = count;
        this.results = results;
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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }

}
