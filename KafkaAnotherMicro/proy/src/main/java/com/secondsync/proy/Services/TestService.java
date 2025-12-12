package com.secondsync.proy.Services;

import org.apache.kafka.clients.producer.ProducerRecord;
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
        this.senderExample.send("micro1",3,null, message); //completable future
      //  this.senderExample.send("micro1", 0, 12, message);
        
        return "Ok";
    }

    public String triggerPojoSend(){
        MicroOneRequest obj = MicroOneRequest.builder().name("REKSI nmandao del micro").id(199).description("mero god").age(199).build();
        this.pojoSender.send("micro2",obj);
        return "Yeah";
    }

    public String sendToListenerTest(){
        //this.pojoSender.send(new ProducerRecord<>("micro1", 1, null, "prueba"));
        System.out.println("AQUI SE MANDA EL LISTENER");
        this.senderExample.send(new ProducerRecord<>("micro1", 2, null, "pruebaskiSpringddddd"));
        return "Ok";
    }
}
