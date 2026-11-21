package com.L.controller;

import com.L.pojo.Orders;
import com.L.service.center.MyOrdersService;
import com.L.utils.LJSONResult;

import java.io.File;

public class BaseController {

    @Autowired
    public MyOrdersService myOrdersService;

    public static final String FOODIE_SHOPCART = "shopcart";


    public static final Integer COMMON_PAGE_SIZE = 10;
    public static final Integer PAGE_SIZE = 20;

//    支付中心的调用地址
    String paymentUrl = "";

    // 微信支付成功 -> 支付中心 -> 天天吃货平台
    //                       |-> 回调通知的url
    String payReturnUrl = "http://内网穿透地址/orders/notifyMerchantOrderPaid";

    // 用户上传头像的位置
    public static final String IMAGE_USER_FACE_LOCATION = File.separator + "workspaces" +
                                                            File.separator + "images" +
                                                            File.separator + "foodie" +
                                                            File.separator + "faces";

    // public static final String IMAGE_USER_FACE_LOCATION = "/workspaces/images/foodie/faces";

    /**
     * 用于验证用户和订单是否有关联关系，避免非法用户调用
     *
     * @return
     */
    public LJSONResult checkUserOrder(String userId, String orderId) {
        Orders order = myOrdersService.queryMyOrder(userId, orderId);
        if (order == null) {
            return LJSONResult.errorMsg("订单不存在！");
        }
        return LJSONResult.ok(order);
    }

}
