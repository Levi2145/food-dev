package com.L.utils;
import java.util.Collections;
import java.util.List;
public class PagedGridResult {
    private Integer page; private Integer total; private Integer records; private List<?> rows;
    public PagedGridResult() { this(1, 0, 0, Collections.emptyList()); }
    public PagedGridResult(Integer page, Integer total, Integer records, List<?> rows) { this.page=page; this.total=total; this.records=records; this.rows=rows; }
    public Integer getPage(){return page;} public Integer getTotal(){return total;} public Integer getRecords(){return records;} public List<?> getRows(){return rows;}
    public void setPage(Integer v){page=v;} public void setTotal(Integer v){total=v;} public void setRecords(Integer v){records=v;} public void setRows(List<?> v){rows=v;}
}
