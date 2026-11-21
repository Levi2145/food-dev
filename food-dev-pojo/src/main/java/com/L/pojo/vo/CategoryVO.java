package com.L.pojo.vo;
import java.util.List;
import lombok.Data;
@Data
/*
* 二级分类vo
* */
public class CategoryVO {
    private Integer id;
    private String name;
    private String type;
    private Integer fatherId;
//    三级分类vo list
    private List<SubCategoryVo> subCatList;
}
