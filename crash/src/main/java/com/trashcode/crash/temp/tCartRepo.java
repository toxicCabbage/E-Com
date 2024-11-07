package com.trashcode.crash.temp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface tCartRepo extends JpaRepository<tCart, Integer> {
   tCart findByid(int i);
}
