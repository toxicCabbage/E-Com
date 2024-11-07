package com.trashcode.crash.Service;

import com.trashcode.crash.productRepo.productRepo;
import com.trashcode.crash.product_entity.product_entity;
import com.trashcode.crash.user_entity.user_entity;
import com.trashcode.crash.user_repository.user_repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PageHandler {

    @Autowired
    productRepo pRepo;

    @Autowired
    private user_repository uReop;

    public Page<product_entity> getProductPageByPriceFillter(int pageNo, String cate, String sortBy, String sortDir,
            int min, int max) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, 8, sort);

        System.out.println("Error in Service Layer");
        return pRepo.findALLproduct_entityByproductCateAndProductPriceGreaterThanAndProductPriceLessThan(cate, min, max,
                pageable);
    }

    public Page<product_entity> getPage(int pageNo, String cate, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, 8, sort);

        System.out.println("Error in Service Layer");
        return pRepo.findALLproduct_entityByproductCate(cate, pageable);
    }

    public Page<product_entity> getPage(int pageNo, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, 10, sort);

        System.out.println("this is new method");
        return pRepo.findAll(pageable);
    }

    public Page<user_entity> getAllUser(int pageNo, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, 10, sort);

        System.out.println("this is new method");
        return uReop.findAll(pageable);
    }

    // GET ALl Product By UserId

    public Page<product_entity> getProductSByuserId(int pageNo, int userId, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, 4, sort);

        System.out.println("Error in Service Layer");
        return pRepo.findALLproduct_entityByuserid(userId, pageable);
    }
}
