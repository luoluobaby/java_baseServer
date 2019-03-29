package com.model.vo;

import java.util.List;

/**
 * @author Jimmy
 * @date 2018/9/10 14:09
 */
public class TradeDataToData {

    private String title ;

    private List<TradeTwoDataToData> item ;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<TradeTwoDataToData> getItem() {
        return item;
    }

    public void setItem(List<TradeTwoDataToData> item) {
        this.item = item;
    }
}