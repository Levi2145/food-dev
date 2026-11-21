package com.L.pojo;
import lombok.Data;
import java.util.Date;
@Data
public class OrderStatus { private String id; private String orderId; private Integer orderStatus; private Date createdTime; private String remark; }
