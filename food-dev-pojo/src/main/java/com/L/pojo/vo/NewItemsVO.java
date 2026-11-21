package com.L.pojo.vo;

/*
* 最新商品VO
* */

import java.util.List;

import lombok.Data;
@Data
public class NewItemsVO {
    private Integer rootCatId;
    private String rootCatName;
    private String slogan;
    private String catImage;
    private String bgColor;

    private List<SimpleItemVO> simpleItemList;
}
