package com.L.pojo.vo;

/*
* 商品详情VO
* */

import com.L.pojo.Items;
import com.L.pojo.ItemsImg;
import com.L.pojo.ItemsParam;
import com.L.pojo.ItemsSpec;

import java.util.List;
import lombok.Data;
@Data
public class ItemInfoVO {

    private Items item;
    private List<ItemsImg> itemImgList;
    private List<ItemsSpec> itemSpecList;
    private ItemsParam itemParam;
}
