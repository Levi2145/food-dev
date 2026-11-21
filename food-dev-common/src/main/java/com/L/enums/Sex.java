package com.L.enums;

public enum Sex {
    /*
    * 男1
    * 女0
    * 保密2
    * */
    FEMALE(0), MALE(1), SECRET(2);
    public final Integer type;
    Sex(Integer type) { this.type = type; }
}
