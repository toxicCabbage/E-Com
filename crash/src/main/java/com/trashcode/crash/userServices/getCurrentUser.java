package com.trashcode.crash.userServices;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trashcode.crash.user_entity.PrincipalUser;
import com.trashcode.crash.user_entity.user_entity;
import com.trashcode.crash.user_repository.PrincipalUserRepo;
import com.trashcode.crash.user_repository.user_repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class getCurrentUser {
    @Autowired
    private user_entity ue;

    @Autowired
    private user_repository userRepo;

    @Autowired
    PrincipalUserRepo pUserRepo;

    public int getUserId(String username) {

        int userId;

        // user_entity ue = userRepo.findByusername(username);
        ue = userRepo.findByusername(username);
        // principalUser = (PrincipalUser) objectMapper(ue);

        System.out.println("*********************************");
        System.out.println("USERNAME :" + username);

        userId = ue.getUserid();
        // System.out.println("currest user name : "+ principalUser.toString());

        // pUserRepo.save(principalUser);

        System.out.println("USERID : " + userId);
        return userId;
    }

    public static Object objectMapper(Object object) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES);
        PrincipalUser principalUser = mapper.convertValue(object, PrincipalUser.class);
        return principalUser;
    }

}
