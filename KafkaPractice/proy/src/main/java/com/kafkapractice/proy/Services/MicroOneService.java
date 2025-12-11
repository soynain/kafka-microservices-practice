package com.kafkapractice.proy.Services;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kafkapractice.proy.Entities.MicroOneEntity;
import com.kafkapractice.proy.Respositories.MicroOneRepo;

@Service
public class MicroOneService {
    @Autowired
    private MicroOneRepo microOneRepo;

    public List<MicroOneEntity> getAllResults(){
        return microOneRepo.findAll();
    }


    @Transactional(noRollbackFor = {SQLException.class})
    public MicroOneEntity saveRegistry(MicroOneEntity obj){
        return microOneRepo.save(obj);
    }

}
