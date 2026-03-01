package ru.project.my.eventnotificator.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import ru.project.my.eventnotificator.kafka.model.EventChangeMessage;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConfig {

    @Bean
    public ConsumerFactory<Long, EventChangeMessage> consumerFactory() {
        Map<String, Object> configProperties = new HashMap<>();
        configProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configProperties.put(ConsumerConfig.GROUP_ID_CONFIG, "notificator-group-1");
        configProperties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        configProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class);

        JsonDeserializer<EventChangeMessage> jsonDeserializer = new JsonDeserializer<>(EventChangeMessage.class);
        jsonDeserializer.setUseTypeHeaders(false);

        DefaultKafkaConsumerFactory<Long, EventChangeMessage> consumerFactory = new DefaultKafkaConsumerFactory<>(configProperties);
        consumerFactory.setValueDeserializer(jsonDeserializer);

        return consumerFactory;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<Long, EventChangeMessage> containerFactory(ConsumerFactory<Long, EventChangeMessage> consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<Long, EventChangeMessage> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);

        return factory;
    }
}
