package com.trashcode.crash.user_entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "tbl_currentUser")
@Data
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Component
public class PrincipalUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userid;
    private String name;
    private String lname;
    private String username;
    private String Email;
    private String mobile_no;
    private String Gender;
    private String landmark;
    private String area;
    private String district;
    private String state;
    private String county;
    private String password;
    private String role;
    private String profilePic;
    private String profileBGPic;
   
}