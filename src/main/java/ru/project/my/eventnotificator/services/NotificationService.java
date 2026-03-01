package ru.project.my.eventnotificator.services;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.project.my.eventnotificator.converters.NotificationEntityConverter;
import ru.project.my.eventnotificator.repositories.NotificationRepository;
import ru.project.my.eventnotificator.repositories.entity.NotificationEntity;
import ru.project.my.eventnotificator.services.model.Notification;

import java.util.List;

@Service
public class NotificationService {
    private final NotificationEntityConverter converter;
    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationEntityConverter converter, NotificationRepository notificationRepository) {
        this.converter = converter;
        this.notificationRepository = notificationRepository;
    }

    public List<Notification> getUserNotifications() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = (Long) authentication.getPrincipal();

        List<NotificationEntity> notifications = notificationRepository.findByRegUserIdAndRead(userId, false);

        return converter.toNotification(notifications);
    }

    @Transactional
    public void markAsRead(List<Long> notificationIds) {
        List<NotificationEntity> notifications = notificationRepository.findByIdIn(notificationIds);

        for (NotificationEntity notification: notifications) {
            notification.setRead(true);
        }
    }
}
