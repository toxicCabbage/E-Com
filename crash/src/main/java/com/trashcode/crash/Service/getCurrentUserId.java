package com.trashcode.crash.Service;

import com.trashcode.crash.userServices.getCurrentUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class getCurrentUserId {

    @Autowired
    private getCurrentUser gCU;

    public int getCurrentUserID(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
         String username = authentication.getName();
         int userId = gCU.getUserId(username);
        return userId;
    }

    
}
