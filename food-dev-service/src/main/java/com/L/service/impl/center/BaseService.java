package com.L.service.impl.center;

import com.L.utils.PagedGridResult;

import java.util.List;

public class BaseService {

    public PagedGridResult setterPagedGrid(List<?> list, Integer page) {
        PagedGridResult grid = new PagedGridResult();
        grid.setPage(page);
        grid.setRows(list);
        grid.setTotal(list == null ? 0 : list.size());
        grid.setRecords(list == null ? 0 : list.size());
        return grid;
    }
}
