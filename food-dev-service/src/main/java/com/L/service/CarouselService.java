package com.L.service;
import com.L.pojo.Carousel;
import java.util.List;

public interface CarouselService {
    /**
     *
     *
     *
     * 查询所有轮播图列表
     *
     * @param isShow areturn
     */
    public List<Carousel> queryAll(Integer isShow);
}
