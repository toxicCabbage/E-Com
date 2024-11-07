package com.trashcode.crash.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class givemepassword {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public givemepassword(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public String getPassword(String simplePassword){

        String encodedPassword = passwordEncoder.encode(simplePassword);
        System.out.println("this is password encoder service");
        System.out.println("encoded passsword :" + encodedPassword);

        return encodedPassword;
    }
    
}
