package ru.project.my.eventnotificator.services;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.project.my.eventnotificator.repositories.NotificationRepository;

import java.time.LocalDateTime;

@Service
public class ScheduledTasksService {
    private final NotificationRepository notificationRepository;

    public ScheduledTasksService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Scheduled(cron = "${eventmanager.delete-old-notifications-cron}")
    @Transactional
    public void deleteOldNotifications() {
        notificationRepository.deleteOldNotifications(LocalDateTime.now().minusDays(7));
    }
}
