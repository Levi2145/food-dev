package com.L.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.L.pojo.vo.CategoryVO;
import com.L.pojo.vo.NewItemsVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapperCustom {
    public List<CategoryVO> getSubCatList(Integer rootCatId);

    public List<NewItemsVO> getSixNewItemsLazy(@Param("paramsMap") Map<String, Object> map);
}
