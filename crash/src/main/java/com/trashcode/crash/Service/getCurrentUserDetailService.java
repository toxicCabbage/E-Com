package com.trashcode.crash.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class getCurrentUserDetailService{

    
    public long getCurrentUserId(HttpServletRequest request){

        long userId = -1;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        HttpSession session = request.getSession();
        if((Long)session.getAttribute("currentUserId")==null){
            userId = (Long)session.getAttribute("currentUserId");
        }

        System.out.println("USER DETAIL SERVICE : USER ID : " + userId);
        return userId;
    }
    
}

