package ru.project.my.eventnotificator.services;

import org.springframework.context.annotation.Lazy;
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
    private final NotificationCounterService notificationCounterService;
    private final NotificationService self;

    public NotificationService(NotificationEntityConverter converter, NotificationRepository notificationRepository, NotificationCounterService notificationCounterService, @Lazy NotificationService notificationService) {
        this.converter = converter;
        this.notificationRepository = notificationRepository;
        this.notificationCounterService = notificationCounterService;
        this.self = notificationService;
    }

    public List<Notification> getUserNotifications() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = (Long) authentication.getPrincipal();

        List<NotificationEntity> notifications = notificationRepository.findByRegUserIdAndRead(userId, false);

        return converter.toNotification(notifications);
    }

    public Long getUserNotificationsCount() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = (Long) authentication.getPrincipal();

        return notificationCounterService.getUnread(userId);
    }

    @Transactional
    public void saveNotifications(List<NotificationEntity> notifications) {
        notificationRepository.saveAll(notifications);
    }

    public void markAsRead(List<Long> notificationIds) {
        List<NotificationEntity> notifications = self.markAsReadInDb(notificationIds);

        for (NotificationEntity notification: notifications) {
            notificationCounterService.syncUnreadFromDatabase(notification.getRegUserId());
        }
    }

    @Transactional
    public List<NotificationEntity> markAsReadInDb(List<Long> notificationIds) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = (Long) authentication.getPrincipal();

        List<NotificationEntity> notifications = notificationRepository.findByRegUserIdAndIdIn(userId, notificationIds);

        for (NotificationEntity notification: notifications) {
            notification.setRead(true);
        }

        return notifications;
    }
}
