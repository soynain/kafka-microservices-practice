package com.secondsync.proy.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.secondsync.proy.Entities.MicroOneRequest;

@Service
public class TestService {
    
    @Autowired
    @Qualifier("basicSender")
    private KafkaTemplate<Integer, String> senderExample;

    @Autowired
    @Qualifier("pojoSender")
    private KafkaTemplate<String,MicroOneRequest> pojoSender;


    public String triggerSavingDBLikeSycronization(String message){
        System.out.println("AQUI  TENDRIA QUE TRIGGEREAR EL ENVIO");
        this.senderExample.send("micro1", message); //completable future
        return "Ok";
    }

    public String triggerPojoSend(){
        MicroOneRequest obj = MicroOneRequest.builder().name("REKSI nmandao del micro").id(199).description("mero god").age(199).build();
        this.pojoSender.send("micro2",obj);
        return "Yeah";
    }
}
