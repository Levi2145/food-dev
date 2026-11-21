package com.L.pojo;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
@Data
@TableName("category")
public class Category { @TableId private Integer id; private String name; private Integer type; private Integer fatherId; private String logo; private Integer sod; }
