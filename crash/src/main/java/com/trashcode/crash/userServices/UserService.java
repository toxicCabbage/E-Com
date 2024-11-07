package com.trashcode.crash.userServices;

import com.trashcode.crash.Service.getCurrentUserId;
import com.trashcode.crash.user_entity.user_entity;
import com.trashcode.crash.user_repository.user_repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements userServices {

    @Autowired
    private user_repository user_repository;

    @Autowired
    private getCurrentUserId GCUID;

    @Override
    public user_entity getUsesrByUserId(int userId) {
        user_entity user = user_repository.findByuserid(userId);
        return user;
    }

}
