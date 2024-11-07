package com.trashcode.crash.user_entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.trashcode.crash.auth.Roles;
import com.trashcode.crash.wallet.Wallet;

import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "tblUser")
@Data
// @AllArgsConstructor
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Component
public class user_entity {
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
    private String profilePic;
    private String profileBGPic;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Set<Roles> roles = new HashSet<>();
  
}
