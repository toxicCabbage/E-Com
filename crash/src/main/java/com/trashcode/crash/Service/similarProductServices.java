package com.trashcode.crash.Service;

import com.trashcode.crash.product_entity.product_entity;

import org.springframework.data.domain.Page;

public interface similarProductServices {

    public Page <product_entity> getPage(int pageNo, String cate, String sortBy, String sortDir);

    
}
