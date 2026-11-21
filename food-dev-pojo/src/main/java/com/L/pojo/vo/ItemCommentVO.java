package com.L.pojo.vo;

import java.util.Date;

/*
* 用于展示商品评价的VO
* */
import lombok.Data;
@Data
public class ItemCommentVO {
    private Integer commentLevel;
    private String content;
    private String specName;
    private Date createdTime;
    private String userFace;
    private String nickname;
}
