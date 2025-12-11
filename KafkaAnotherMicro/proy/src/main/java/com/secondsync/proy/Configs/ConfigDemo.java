package com.secondsync.proy.Configs;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JacksonJsonDeserializer;
import org.springframework.kafka.support.serializer.JacksonJsonSerializer;

import com.secondsync.proy.Entities.MicroOneRequest;
import com.secondsync.proy.Entities.MicroTwo;

@Configuration
@EnableKafka
public class ConfigDemo {

    @Value("${spring.kafka.bootstrap-servers}")
    private String booststrapServer;
    
    @Bean
    public ProducerFactory<Integer, String> producerFactory() {
        return new DefaultKafkaProducerFactory<>(senderProps());
    }

    private Map<String, Object> senderProps() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, booststrapServer);
        props.put(ProducerConfig.LINGER_MS_CONFIG, 10);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        //...
        return props;
    }

    @Bean("basicSender")
    public KafkaTemplate<Integer, String> kafkaTemplate(ProducerFactory<Integer, String> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }

    @Bean
    public ProducerFactory<String, MicroOneRequest> pojoSending() {
        Map<String, Object> props = new HashMap<>();
        JacksonJsonSerializer<MicroOneRequest> c1 = new JacksonJsonSerializer<>();
      //  c1.setAddTypeInfo(false); //asi solo se guia por propiedades
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, booststrapServer);
        props.put(ProducerConfig.LINGER_MS_CONFIG, 10);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JacksonJsonSerializer.class);
        return new DefaultKafkaProducerFactory<String, MicroOneRequest>(senderProps(),new StringSerializer(),c1);
    }

    @Bean("pojoSender")
    public KafkaTemplate<String, MicroOneRequest> pojoSender(ProducerFactory<String, MicroOneRequest> pojoSending) {
        return new KafkaTemplate<>(pojoSending);
    }
}
