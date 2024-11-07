package com.trashcode.crash.auth;

import com.trashcode.crash.user_entity.user_entity;
import com.trashcode.crash.user_repository.user_repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserService implements UserDetailsService{
    @Autowired
    private user_repository user_repository;

   
  

 
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        user_entity user = user_repository.findByusername(username);

        if(user ==null){
            throw new UsernameNotFoundException("User not found");
        }

        return new ApplicationUser(user);
                    
    }




    


   
    
}
