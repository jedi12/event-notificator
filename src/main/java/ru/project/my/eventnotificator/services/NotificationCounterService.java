package ru.project.my.eventnotificator.services;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import ru.project.my.eventnotificator.repositories.NotificationRepository;

@Service
public class NotificationCounterService {
    private final StringRedisTemplate redisTemplate;
    private final NotificationRepository notificationRepository;

    public NotificationCounterService(StringRedisTemplate redisTemplate, NotificationRepository notificationRepository) {
        this.redisTemplate = redisTemplate;
        this.notificationRepository = notificationRepository;
    }

    public void incrementUnread(Long userId, long delta) {
        redisTemplate.opsForValue().increment(unreadKey(userId), delta);
    }

    public void syncUnreadFromDatabase(Long userId) {
        long unreadCount = notificationRepository.countByRegUserIdAndReadFalse(userId);
        redisTemplate.opsForValue().set(unreadKey(userId), Long.toString(unreadCount));
    }

    public long getUnread(Long userId) {
        String count = redisTemplate.opsForValue().get(unreadKey(userId));
        return Long.parseLong(count);
    }

    private String unreadKey(Long userId) {
        return "notif:unread:" + userId;
    }
}
