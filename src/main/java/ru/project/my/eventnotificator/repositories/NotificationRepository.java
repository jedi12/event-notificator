package ru.project.my.eventnotificator.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.project.my.eventnotificator.repositories.entity.NotificationEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {

    List<NotificationEntity> findByRegUserIdAndRead(Long userId, boolean isRead);

    List<NotificationEntity> findByRegUserIdAndIdIn(Long userId, List<Long> ids);

    @Modifying
    @Query("DELETE NotificationEntity n WHERE n.createDate <= :date")
    void deleteOldNotifications(@Param("date") LocalDateTime date);
}
