package com.trashcode.crash.temp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface tProductRepo extends JpaRepository<tProducts, Integer> {

    tProducts findByid(int i);

}
