package com.rupesh.pagination;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import static java.util.Collections.emptyList;

@AllArgsConstructor
@Data
public class PageRequest implements Serializable {

    private Map<String, Object> searchField;
    private int page, limit;

    private List<SortModel> sortModel;
    private OrderType orderType;

    public PageRequest() {
        this.sortModel = emptyList();
        this.page = 1;
        this.limit = 10;
    }

    public int getPage() {
        return page;
    }
}


