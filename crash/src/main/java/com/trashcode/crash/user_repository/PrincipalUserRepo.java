package com.trashcode.crash.user_repository;

import com.trashcode.crash.user_entity.PrincipalUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrincipalUserRepo extends JpaRepository<PrincipalUser, Integer> {

   String deleteAllByusername(String userName);
}
