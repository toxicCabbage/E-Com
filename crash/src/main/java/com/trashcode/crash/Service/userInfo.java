package com.trashcode.crash.Service;

import com.trashcode.crash.user_entity.user_entity;
import com.trashcode.crash.user_repository.user_repository;

import org.springframework.beans.factory.annotation.Autowired;


public class userInfo implements userInformation{
    @Autowired
    private user_entity userEntity;
    
    @Autowired
    private user_repository uRepo;

    @Override
    public user_entity getUserProfile(int UserID) {
        return uRepo.findByuserid(UserID);
    }

    
    
}
