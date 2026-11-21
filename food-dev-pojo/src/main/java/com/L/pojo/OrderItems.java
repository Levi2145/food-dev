package com.L.pojo;
import lombok.Data;
import java.math.BigDecimal;
@Data
public class OrderItems { private String id; private String orderId; private String itemId; private String itemName; private String itemSpecId; private String itemSpecName; private BigDecimal price; private Integer buyCounts; private BigDecimal totalAmount; }
