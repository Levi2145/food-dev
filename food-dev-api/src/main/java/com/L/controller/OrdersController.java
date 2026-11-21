package com.L.controller;

import com.L.enums.OrderStatusEnum;
import com.L.enums.PayMethod;
import com.L.pojo.OrderStatus;
import com.L.pojo.bo.SubmitOrderBO;
import com.L.pojo.vo.MerchantOrdersVO;
import com.L.pojo.vo.OrderVO;
import com.L.service.OrderService;
import com.L.utils.CookieUtils;
import com.L.utils.LJSONResult;

import java.awt.*;
import java.net.http.HttpHeaders;

@Api(value = "订单相关", tags = {"订单相关的api接口"})
@RequestMapping("orders")
@RestController
public class OrdersController extends BaseController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private RestTemplate restTemplate;

    @ApiOperation(value = "用户下单", notes = "用户下单", httpMethod = "POST")
    @PostMapping("/create")
    public LJSONResult create(
            @RequestBody SubmitOrderBO submitOrderBO,
            HttpServletRequest request,
            HttpServletResponse response) {

        if (submitOrderBO.getPayMethod() != PayMethod.WEIXIN.type
                && submitOrderBO.getPayMethod() != PayMethod.ALIPAY.type) {
            return LJSONResult.errorMsg("支付方式不支持！ ");
        }
//        1.创建订单
        OrderVO orderVO = orderService.createOrder(submitOrderBO);
        String orderId = orderVO.getOrderId();

//        2.创建订单后，移除购物车中已结算(已提交)的商品
//        TODO 整合redis之后，完善购物车中的已结算商品清除，并且同步到前端的cookie
        CookieUtils.setCookie(request, response, FOODIE_SHOPCART, "", true);

//        3.向支付中心发送当前订单,用于保存支付中心的订单数据
        MerchantOrdersVO merchantOrdersVO = orderVO.getMerchantOrdersVO();
        merchantOrdersVO.setReturnUrl(payReturnUrl);

        // 为了方便测试购买，所以所有的支付金额都统一改为1分钱
        merchantOrdersVO.setAmount(1);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(PageAttributes.MediaType.APPLICATION_JSON);
        headers.add("LUserId", "L");
        headers.add("password", "L");

        HttpEntity<MerchantOrdersVO> entity =
                new HttpEntity<>(merchantOrdersVO, headers);

        ResponseEntity<LJSONResult> responseEntity = restTemplate
                .postForEntity(paymentUrl, entity, LJSONResult.class);
        LJSONResult paymentResult = responseEntity.getBody();
        if (paymentResult.getStatus() != 200) {
            return LJSONResult.errorMsg("支付中心订单创建失败，请联系管理员！");
        }

        return LJSONResult.ok(orderId);
    }

    @PostMapping("notifyMerchantOrderPaid")
    public Integer notifyMerchantOrderPaid(String merchantOrderId) {
        orderService.updateOrderStatus(merchantOrderId, OrderStatusEnum.WAIT_DELIVER.type);
        return HttpStatus.OK.value();
    }

    @PostMapping("getPaidOrderInfo")
    public LJSONResult getPaidOrderInfo(String orderId) {

        OrderStatus orderStatus = orderService.queryOrderStatusInfo(orderId);
        return LJSONResult.ok(orderStatus);
    }
}
