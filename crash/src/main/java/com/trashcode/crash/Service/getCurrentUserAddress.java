package com.trashcode.crash.Service;

import com.trashcode.crash.user_entity.user_entity;
import com.trashcode.crash.user_repository.user_repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class getCurrentUserAddress {

    @Autowired 
    private user_repository URepo;

    @Autowired
    private getCurrentUserId GCUID;

    public String getAddress(){
        int userId = GCUID.getCurrentUserID();
         user_entity ue = URepo.findByuserid(userId);
         String address  = ue.getArea()+ " " + ue.getLandmark()+ " " +ue.getDistrict() + " " + ue.getState() + " " + ue.getCounty()
                            + " " + ue.getMobile_no();
        return address;
    }
    
}
