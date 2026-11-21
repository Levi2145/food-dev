package com.L.pojo.vo;

/*
* 用于展示商品搜素列表结果的VO
* */
import lombok.Data;
@Data
public class SearchItemsVO {

    private String itemId;
    private String itemName;
    private int sellCounts;
    private String imgUrl;
    private int price;
}
