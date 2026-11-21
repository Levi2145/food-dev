package com.L.pojo;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import java.util.Date;
@Data
@TableName("users")
public class Users { @TableId private String id; private String username; private String password; private String nickname; private String face; private String mobile; private String email; private Integer sex; private Date createdTime; private Date updatedTime; }
