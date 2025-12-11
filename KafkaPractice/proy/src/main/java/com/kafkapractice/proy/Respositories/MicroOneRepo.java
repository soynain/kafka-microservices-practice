package com.kafkapractice.proy.Respositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kafkapractice.proy.Entities.MicroOneEntity;

@Repository
public interface MicroOneRepo extends JpaRepository<MicroOneEntity,Integer>{
    
}
