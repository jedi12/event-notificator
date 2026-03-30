package ru.project.my.eventnotificator.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import ru.project.my.eventnotificator.repositories.NotificationRepository;

@Service
public class NotificationCounterService {
    private static final Logger log = LoggerFactory.getLogger(NotificationCounterService.class);

    private final StringRedisTemplate redisTemplate;
    private final NotificationRepository notificationRepository;

    public NotificationCounterService(StringRedisTemplate redisTemplate, NotificationRepository notificationRepository) {
        this.redisTemplate = redisTemplate;
        this.notificationRepository = notificationRepository;
    }

    public void incrementUnread(Long userId, long delta) {
        try {
            redisTemplate.opsForValue().increment(unreadKey(userId), delta);
        } catch (RedisConnectionFailureException e) {
            log.warn("Redis недоступен при увеличении счетчика непрочитанных сообщений для пользователя {}", userId);
        }
    }

    public void syncUnreadFromDatabase(Long userId) {
        try {
            long unreadCount = notificationRepository.countByRegUserIdAndReadFalse(userId);
            redisTemplate.opsForValue().set(unreadKey(userId), Long.toString(unreadCount));
        } catch (RedisConnectionFailureException e) {
            log.warn("Redis недоступен при синхронизации счетчика непрочитанных сообщений для пользователя {}", userId);
        }
    }

    public long getUnread(Long userId) {
        try {
            String count = redisTemplate.opsForValue().get(unreadKey(userId));
            if (count != null) {
                return Long.parseLong(count);
            }
        } catch (RedisConnectionFailureException e) {
            log.warn("Redis недоступен при получении значения счетчика непрочитанных сообщений для пользователя {}", userId);
        }

        return notificationRepository.countByRegUserIdAndReadFalse(userId);
    }

    private String unreadKey(Long userId) {
        return "notif:unread:" + userId;
    }
}
