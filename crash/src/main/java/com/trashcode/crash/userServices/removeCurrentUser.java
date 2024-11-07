package com.trashcode.crash.userServices;

import com.trashcode.crash.user_entity.PrincipalUser;
import com.trashcode.crash.user_repository.PrincipalUserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class removeCurrentUser {
    @Autowired
    PrincipalUser pUser;

    @Autowired
    PrincipalUserRepo pRepo;

    public void removeUser(String userName){

        pRepo.deleteAllByusername(userName);

    }
    
}
