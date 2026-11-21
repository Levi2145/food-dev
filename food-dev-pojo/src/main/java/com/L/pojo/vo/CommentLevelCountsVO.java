package com.L.pojo.vo;

/*
* 用于展示商品评价数量的vo
* */
import lombok.Data;
@Data
public class CommentLevelCountsVO {

    public Integer totalCounts;
    public Integer goodCounts;
    public Integer normalCounts;
    public Integer badCounts;
}
