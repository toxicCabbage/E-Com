package com.trashcode.crash.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<Roles,Integer>{

    @Query("From Roles r WHERE r.Role = ?1")
    Roles findByRoleName(String r);
    
}
