package com.L.pojo;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;
@Data
public class Orders { private String id; private String orderId; private String userId; private String receiverName; private String receiverMobile; private BigDecimal totalAmount; private Integer payMethod; private Integer status; private Date createdTime; private Date payTime; }
