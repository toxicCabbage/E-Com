package com.trashcode.crash.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.trashcode.crash.user_entity.user_entity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class ApplicationUser implements UserDetails {

    private user_entity user_entity;

    public ApplicationUser(user_entity user_entity) {
        this.user_entity = user_entity;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        Set<Roles> roles = user_entity.getRoles();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Roles role : roles) {
            System.out.println("**************************************************************");
            System.out.println("CURRENT USER ROLE :" + role.getRole());
            authorities.add(new SimpleGrantedAuthority(role.getRole()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return user_entity.getPassword();
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return user_entity.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }

}
