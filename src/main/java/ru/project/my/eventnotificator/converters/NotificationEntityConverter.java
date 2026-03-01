package ru.project.my.eventnotificator.converters;

import org.springframework.stereotype.Component;
import ru.project.my.eventnotificator.kafka.model.EventChangeMessage;
import ru.project.my.eventnotificator.kafka.model.FieldChange;
import ru.project.my.eventnotificator.repositories.entity.NotificationEntity;
import ru.project.my.eventnotificator.services.model.Notification;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class NotificationEntityConverter {

    public Notification toNotification(NotificationEntity entity) {
        if (entity == null) return null;

        Notification notification = new Notification();
        notification.setId(entity.getId());
        notification.setEventId(entity.getEventId());
        notification.setChangedByUserId(entity.getChangedByUserId());
        notification.setOwnerId(entity.getOwnerId());

        if (entity.getOldName() != null || entity.getNewName() != null) {
            notification.setName(new FieldChange<>(entity.getOldName(), entity.getNewName()));
        }

        if (entity.getOldMaxPlaces() != null || entity.getNewMaxPlaces() != null) {
            notification.setMaxPlaces(new FieldChange<>(entity.getOldMaxPlaces(), entity.getNewMaxPlaces()));
        }

        if (entity.getOldOccupiedPlaces() != null || entity.getNewOccupiedPlaces() != null) {
            notification.setOccupiedPlaces(new FieldChange<>(entity.getOldOccupiedPlaces(), entity.getNewOccupiedPlaces()));
        }

        if (entity.getOldDate() != null || entity.getNewDate() != null) {
            notification.setDate(new FieldChange<>(entity.getOldDate(), entity.getNewDate()));
        }

        if (entity.getOldCost() != null || entity.getNewCost() != null) {
            notification.setCost(new FieldChange<>(entity.getOldCost(), entity.getNewCost()));
        }

        if (entity.getOldDuration() != null || entity.getNewDuration() != null) {
            notification.setDuration(new FieldChange<>(entity.getOldDuration(), entity.getNewDuration()));
        }

        if (entity.getOldLocationId() != null || entity.getNewLocationId() != null) {
            notification.setLocationId(new FieldChange<>(entity.getOldLocationId(), entity.getNewLocationId()));
        }

        if (entity.getOldStatus() != null || entity.getNewStatus() != null) {
            notification.setStatus(new FieldChange<>(entity.getOldStatus(), entity.getNewStatus()));
        }

        return notification;
    }

    public List<Notification> toNotification(List<NotificationEntity> notificationEntityList) {
        if (notificationEntityList == null) return Collections.emptyList();
        return notificationEntityList.stream().map(this::toNotification).toList();
    }

    public List<NotificationEntity> toEntity(EventChangeMessage eventChangeMessage) {
        if (eventChangeMessage == null || eventChangeMessage.getUsers() == null) return Collections.emptyList();

        List<NotificationEntity> notifications = new ArrayList<>();
        for (Long regUserId : eventChangeMessage.getUsers()) {
            NotificationEntity notificationEntity = new NotificationEntity();
            notificationEntity.setId(null);
            notificationEntity.setEventId(eventChangeMessage.getEventId());
            notificationEntity.setChangedByUserId(eventChangeMessage.getChangedByUserId());
            notificationEntity.setOwnerId(eventChangeMessage.getOwnerId());
            notificationEntity.setRegUserId(regUserId);
            notificationEntity.setOldName(eventChangeMessage.getName() == null ? null : eventChangeMessage.getName().getOldField());
            notificationEntity.setNewName(eventChangeMessage.getName() == null ? null : eventChangeMessage.getName().getNewField());
            notificationEntity.setOldMaxPlaces(eventChangeMessage.getMaxPlaces() == null ? null : eventChangeMessage.getMaxPlaces().getOldField());
            notificationEntity.setNewMaxPlaces(eventChangeMessage.getMaxPlaces() == null ? null : eventChangeMessage.getMaxPlaces().getNewField());
            notificationEntity.setOldOccupiedPlaces(eventChangeMessage.getOccupiedPlaces() == null ? null : eventChangeMessage.getOccupiedPlaces().getOldField());
            notificationEntity.setNewOccupiedPlaces(eventChangeMessage.getOccupiedPlaces() == null ? null : eventChangeMessage.getOccupiedPlaces().getNewField());
            notificationEntity.setOldDate(eventChangeMessage.getDate() == null ? null : eventChangeMessage.getDate().getOldField());
            notificationEntity.setNewDate(eventChangeMessage.getDate() == null ? null : eventChangeMessage.getDate().getNewField());
            notificationEntity.setOldCost(eventChangeMessage.getCost() == null ? null : eventChangeMessage.getCost().getOldField());
            notificationEntity.setNewCost(eventChangeMessage.getCost() == null ? null : eventChangeMessage.getCost().getNewField());
            notificationEntity.setOldDuration(eventChangeMessage.getDuration() == null ? null : eventChangeMessage.getDuration().getOldField());
            notificationEntity.setNewDuration(eventChangeMessage.getDuration() == null ? null : eventChangeMessage.getDuration().getNewField());
            notificationEntity.setOldLocationId(eventChangeMessage.getLocationId() == null ? null : eventChangeMessage.getLocationId().getOldField());
            notificationEntity.setNewLocationId(eventChangeMessage.getLocationId() == null ? null : eventChangeMessage.getLocationId().getNewField());
            notificationEntity.setOldStatus(eventChangeMessage.getStatus() == null ? null : eventChangeMessage.getStatus().getOldField());
            notificationEntity.setNewStatus(eventChangeMessage.getStatus() == null ? null : eventChangeMessage.getStatus().getNewField());

            notifications.add(notificationEntity);
        }

        return notifications;
    }
}
