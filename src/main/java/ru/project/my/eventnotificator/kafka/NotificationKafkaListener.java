package ru.project.my.eventnotificator.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.project.my.eventnotificator.converters.NotificationEntityConverter;
import ru.project.my.eventnotificator.kafka.model.EventChangeMessage;
import ru.project.my.eventnotificator.repositories.entity.NotificationEntity;
import ru.project.my.eventnotificator.services.NotificationCounterService;
import ru.project.my.eventnotificator.services.NotificationService;

import java.util.List;

@Component
public class NotificationKafkaListener {
    static final Logger log = LoggerFactory.getLogger(NotificationKafkaListener.class.getName());

    private final NotificationEntityConverter converter;
    private final NotificationService notificationService;
    private final NotificationCounterService notificationCounterService;

    public NotificationKafkaListener(NotificationEntityConverter converter, NotificationService notificationService, NotificationCounterService notificationCounterService) {
        this.converter = converter;
        this.notificationService = notificationService;
        this.notificationCounterService = notificationCounterService;
    }

    @KafkaListener(topics = "${eventnotificator.kafka.event-change-topic-name}", containerFactory = "containerFactory")
    public void listenEvents(ConsumerRecord<Long, EventChangeMessage> kafkaMessage) {
        EventChangeMessage message = kafkaMessage.value();
        log.info("Из Кафки пришло сообщение: {}", message);

        List<NotificationEntity> notifications = converter.toEntity(message);
        notificationService.saveNotifications(notifications);

        for (NotificationEntity notification: notifications) {
            notificationCounterService.incrementUnread(notification.getRegUserId(), 1);
        }

        log.info("Успешно обработано сообщение из Кафки: {}", message);
    }
}
