package com.L.service.impl;

import com.L.service.CarouselService;

@Service
public class CarouselServiceImpl implements CarouselService {

    @Autowired
    private CarouselMapper carouselMapper;

    public List<Carousel> queryAll(Integer isShow) {

        List<Carousel> result = carouselMapper.select();

        return result;
    }
}
