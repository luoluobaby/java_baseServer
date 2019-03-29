package com.model.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jimmy
 * @date 2018/9/14 17:12
 */
public class DataTwo {
    private String parentId ;
    private List<String> items;

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }

    public DataTwo(String parentId) {
        this.parentId = parentId;
        this.items = new ArrayList<String>();
    }
}
