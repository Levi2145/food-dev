package com.L.pojo;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import java.math.BigDecimal;
import java.util.Date;
@Data
@TableName("items")
public class Items { @TableId private String id; private String itemName; private Integer catId; private Integer rootCatId; private Integer sellCounts; private BigDecimal price; private String mainImg; private String content; private Date createdTime; }
