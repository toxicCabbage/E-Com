package com.trashcode.crash.Service;

import com.trashcode.crash.productRepo.productRepo;
import com.trashcode.crash.product_entity.product_entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class similarProductService implements similarProductServices{
    @Autowired
    private productRepo Prepo;
    
    @Override
    public Page <product_entity> getPage(int pageNo, String term, String sortBy, String sortDir){

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, 4, sort);
        System.out.println("term is serviece layer : " );
        System.out.println("Error in Service Layer");
        return Prepo.findByproductNameIsContaining(term, pageable);
    }
}
