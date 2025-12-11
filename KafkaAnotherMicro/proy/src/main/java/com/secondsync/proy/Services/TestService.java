package com.secondsync.proy.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    
    @Autowired
    private KafkaTemplate<Integer, String> senderExample;

    public String triggerSavingDBLikeSycronization(String message){
        System.out.println("AQUI  TENDRIA QUE TRIGGEREAR EL ENVIO");
        this.senderExample.send("micro1", message); //completable future
        return "Ok";
    }
}
