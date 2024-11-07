package com.trashcode.crash.auth;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class roleConfig {
    
    @Autowired
    private RoleRepo roleRepo;

    public Set <Roles> addRole(String role){

        Roles r = roleRepo.findByRoleName(role);
        int roleId = r.getId();

        Set <Roles> roles = new HashSet<>();
        roles.add(r);
        return roles;

        

    }

}
