package com.L.enums;

/*
* 商品评价等级 枚举
* */

public enum CommentLevel {
    GOOD(1), NORMAL(2), BAD(3);
    public final Integer type;
    CommentLevel(Integer type) { this.type = type; }
}
