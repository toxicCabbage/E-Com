package com.trashcode.crash.autoComplete;

import java.util.List;

import com.trashcode.crash.productRepo.productRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class autoCompleteService {
    @Autowired
    private productRepo PR;

    public List<String> getProductsName(String term){
        
        return PR.getproductName(term);
    }
    
}
