package com.L.service.impl;
import com.L.pojo.Category;
import com.L.pojo.vo.CategoryVO;
import com.L.pojo.vo.NewItemsVO;
import com.L.service.CategoryService;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {
    public List<Category> queryAllRootLevelCat() { return Collections.emptyList(); }
    public List<CategoryVO> getSubCatList(Integer rootCatId) { return Collections.emptyList(); }
    public List<NewItemsVO> getSixNewItemsLazy(Integer rootCatId) { return Collections.emptyList(); }
}
