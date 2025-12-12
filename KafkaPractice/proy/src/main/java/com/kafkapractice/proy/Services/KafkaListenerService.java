package com.kafkapractice.proy.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Service;

import com.kafkapractice.proy.Entities.MicroOneEntity;

@Service
public class KafkaListenerService {
    @Value("${exampleValue}")
    private String prueba;

    @Autowired
    private MicroOneService microOneService;

    @KafkaListener(id="listen",groupId = "0",topicPartitions = { @TopicPartition(topic = "micro1", partitions = { "3"})})
    public void listen(String in){
        /**Un pojo de prueba */
        MicroOneEntity obj = MicroOneEntity.builder().age(55)
        .description("prueba de evento registrar").name("Matias josancho").build();

        MicroOneEntity entitySetter = microOneService.saveRegistry(obj);

        System.out.println(entitySetter.toString());

        System.out.println("SI EMPIEZA LA TRANSACCIÓN");

        /*Con helper o libreria busca la forma más optima de guardar el resultado de la transacción */
    }

    @KafkaListener(id="listen2",topics = "micro2",groupId = "2",containerFactory="kafkaListenerForDeserialize")
    public void deserializationTest(MicroOneEntity obj){
        System.out.println("DEBE RECIBIR EL JSON DESDE EL EVENT Y PARSEARLO A POJO CORRECTAMENTE: ".concat(obj.toString()));
    }


    /**A partir de un mismo tópico, podemos definir las particiones pertinentes */
    @KafkaListener(id="listen3",topicPartitions = { @TopicPartition(topic = "micro1", partitions = { "2"})},groupId = "2")
    public void listen3(String in){
        /**Un pojo de prueba */
      System.out.println(in+" mensaje listen 2");
    }

    @KafkaListener(id="listen4",topicPartitions = { @TopicPartition(topic = "micro1", partitions = { "1","2"})},groupId = "2")
    public void listen4(String in){
        /**Un pojo de prueba */
      System.out.println(in+" mensaje listen 3");
    }

    
}
