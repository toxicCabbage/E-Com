package com.trashcode.crash.Service;

import com.trashcode.crash.security.givemepassword;
import com.trashcode.crash.user_repository.user_repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class currentPasswordMatcher {

@Autowired
private user_repository uRepo;
@Autowired
private getCurrentUserId getCurrentUserId;
@Autowired
private givemepassword gMP;



public boolean passwordMatched(String CurrentPassword, String newPassword){

    int userId = getCurrentUserId.getCurrentUserID();
    String Password = uRepo.getPassword(userId);
    System.out.println("DATABASE PASSWORD : " + Password);
    System.out.println("CURRENT PASSWORD : " + CurrentPassword);
    String ecodedPassword = gMP.getPassword(CurrentPassword);
    System.out.println("encoded Password : "+ ecodedPassword);

    BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();

    Boolean isPasswordMatched = bcrypt.matches(CurrentPassword, Password);
    System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
    System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
    System.out.println("Resutl :" + " " + isPasswordMatched);







    if(isPasswordMatched){
        System.out.println("##############password Matched ##################");
        updatePassword(newPassword, userId);
        
        return true;
    }
    else{
        System.out.println("Password Did not matched");
    }

    return false;
}

public void updatePassword(String password,int uId){

    String encodedPassword = gMP.getPassword(password);
    uRepo.updatePassword(encodedPassword, uId);

}
    
    
}
