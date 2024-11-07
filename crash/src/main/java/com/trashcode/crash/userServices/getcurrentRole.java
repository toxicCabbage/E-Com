package com.trashcode.crash.userServices;

import java.util.Set;

import com.trashcode.crash.Service.getCurrentUserId;
import com.trashcode.crash.auth.Roles;
import com.trashcode.crash.user_entity.user_entity;
import com.trashcode.crash.user_repository.user_repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class getcurrentRole {

    @Autowired
    private getCurrentUserId GCUID;

    @Autowired
    private user_repository UserRepo;

    public String getRole() {

        Set<Roles> roles = UserRepo.findByuserid(GCUID.getCurrentUserID()).getRoles();
        String currentRole = "";
        for (Roles role : roles) {

            System.out.println("CURRENT USER ROLE :" + role.getRole());
            currentRole = role.getRole();
        }

        return currentRole;
    }

}
