package com.L.controller;

import com.L.pojo.Carousel;
import com.L.pojo.Category;
import com.L.pojo.vo.NewItemsVO;
import com.L.service.CarouselService;
import com.L.service.CategoryService;
import com.L.utils.LJSONResult;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import com.L.enums.YesOrNo;
import io.swagger.annotations.*;

@RestController
@RequestMapping("/index")
public class IndexController {

    @Autowired
    private CarouselService carouselService;

    @Autowired
    private CategoryService categoryService;



    @GetMapping("/carousel")
    public LJSONResult carousel(){
        List<Carousel> list = carouselService.queryAll(YesOrNo.YES.type);
        return LJSONResult.ok(list);
    }

    @GetMapping("/cats")
    public LJSONResult cats(){
        List<Carousel> list = categoryService.queryAllRootLevelCat();
        return LJSONResult.ok(list);
    }

    @GetMapping("/subCat/{rootCatId}")
    public LJSONResult subcat(@PathVariable Integer rootCatId){
//        判空处理
        List<Category> list = categoryService.getSubCatList(rootCatId);
        return LJSONResult.ok(list);
    }

    @ApiOperation(value = "查询每个一级分类下的最新6条商品数据", notes = "查询每个一级分类下的最新6条商品数据",httpMethod = "GET")
    @GetMapping("/sixNewItems/{rootCatId}")
    public LJSONResult sixNewItems(
            @ApiParam(name = "rootCatId", value = "一级分类id", required = true)
            @PathVariable Integer rootCatId) {

        if (rootCatId == null) {
            return LJSONResult.errorMsg("分类不存在");
        }

        List<NewItemsVO> list = categoryService.getSixNewItemsLazy(rootCatId);
        return LJSONResult.ok(list);
    }
}
