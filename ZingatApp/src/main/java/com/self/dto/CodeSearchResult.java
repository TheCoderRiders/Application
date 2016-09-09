package com.self.dto;

import com.self.pojo.ActualCode;

import java.util.List;

/**
 * Created by akash.p on 5/9/16.
 */
public class CodeSearchResult {

    private Long total;
    private Integer start;
    private Integer pageSize;
    private List<ActualCode> codes;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<ActualCode> getCodes() {
        return codes;
    }

    public void setCodes(List<ActualCode> codes) {
        this.codes = codes;
    }
}
