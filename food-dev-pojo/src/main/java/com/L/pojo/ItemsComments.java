package com.L.pojo;
import lombok.Data;
import java.util.Date;
@Data
public class ItemsComments { private String id; private String userId; private String itemId; private String orderId; private Integer commentLevel; private String content; private Date createdTime; }
