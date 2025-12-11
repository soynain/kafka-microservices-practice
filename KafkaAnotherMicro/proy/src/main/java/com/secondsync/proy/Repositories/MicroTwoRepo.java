package com.secondsync.proy.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.secondsync.proy.Entities.MicroTwo;

@Repository
public interface MicroTwoRepo extends JpaRepository<MicroTwo,Integer>{
    
}
