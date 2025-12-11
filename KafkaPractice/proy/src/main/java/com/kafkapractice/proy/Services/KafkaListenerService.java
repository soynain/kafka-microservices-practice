package com.kafkapractice.proy.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.kafkapractice.proy.Entities.MicroOneEntity;

@Service
public class KafkaListenerService {
    @Value("${exampleValue}")
    private String prueba;

    @Autowired
    private MicroOneService microOneService;

    @KafkaListener(id="listen",topics="micro1")
    public void listen(String in){
        /**Un pojo de prueba */
        MicroOneEntity obj = MicroOneEntity.builder().age(55)
        .description("prueba de evento registrar").name("Matias josancho").build();

        MicroOneEntity entitySetter = microOneService.saveRegistry(obj);

        System.out.println(entitySetter.toString());

        System.out.println("SI EMPIEZA LA TRANSACCIÓN");

        /*Con helper o libreria busca la forma más optima de guardar el resultado de la transacción */
    }

    
}
