package com.L.pojo;
import lombok.Data;
import java.math.BigDecimal;
@Data
public class ItemsSpec { private String id; private String itemId; private String name; private Integer stock; private BigDecimal discounts; private BigDecimal priceDiscount; }
