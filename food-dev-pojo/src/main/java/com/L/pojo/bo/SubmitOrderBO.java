package com.L.pojo.bo;

/*
* 用于创建订单的BO对象
* */
import lombok.Data;
@Data
public class SubmitOrderBO {

    private String userId;
    private String itemSpecIds;
    private String addressId;
    private Integer payMethod;
    private String leftMsg;
}
