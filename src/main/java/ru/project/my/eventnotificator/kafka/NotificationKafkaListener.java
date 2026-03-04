package ru.project.my.eventnotificator.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.project.my.eventnotificator.converters.NotificationEntityConverter;
import ru.project.my.eventnotificator.kafka.model.EventChangeMessage;
import ru.project.my.eventnotificator.repositories.NotificationRepository;
import ru.project.my.eventnotificator.repositories.entity.NotificationEntity;

import java.util.List;

@Component
public class NotificationKafkaListener {
    static final Logger log = LoggerFactory.getLogger(NotificationKafkaListener.class.getName());

    private final NotificationEntityConverter converter;
    private final NotificationRepository repository;

    public NotificationKafkaListener(NotificationEntityConverter converter, NotificationRepository repository) {
        this.converter = converter;
        this.repository = repository;
    }

    @KafkaListener(topics = "${eventnotificator.kafka.event-change-topic-name}", containerFactory = "containerFactory")
    @Transactional
    public void listenEvents(ConsumerRecord<Long, EventChangeMessage> kafkaMessage) {
        EventChangeMessage message = kafkaMessage.value();
        log.info("Из Кафки пришло сообщение: {}", message);

        List<NotificationEntity> notifications = converter.toEntity(message);
        repository.saveAll(notifications);

        log.info("Успешно обработано сообщение из Кафки: {}", message);
    }
}
