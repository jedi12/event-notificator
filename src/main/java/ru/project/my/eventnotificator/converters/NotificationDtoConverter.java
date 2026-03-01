package ru.project.my.eventnotificator.converters;

import org.springframework.stereotype.Component;
import ru.project.my.eventnotificator.controllers.dto.FieldChangeDateTime;
import ru.project.my.eventnotificator.controllers.dto.FieldChangeInteger;
import ru.project.my.eventnotificator.controllers.dto.FieldChangeLong;
import ru.project.my.eventnotificator.controllers.dto.FieldChangeString;
import ru.project.my.eventnotificator.controllers.dto.NotificationDto;
import ru.project.my.eventnotificator.services.model.Notification;

import java.util.Collections;
import java.util.List;

@Component
public class NotificationDtoConverter {

    public NotificationDto toDto(Notification notification) {
        if (notification == null) return null;

        NotificationDto dto = new NotificationDto();
        dto.setEventId(notification.getEventId());

        if (notification.getName() != null) {
            dto.setName(new FieldChangeString(notification.getName().getOldField(), notification.getName().getNewField()));
        }

        if (notification.getMaxPlaces() != null) {
            dto.setMaxPlaces(new FieldChangeInteger(notification.getMaxPlaces().getOldField(), notification.getMaxPlaces().getNewField()));
        }

        if (notification.getDate() != null) {
            dto.setDate(new FieldChangeDateTime(notification.getDate().getOldField(), notification.getDate().getNewField()));
        }

        if (notification.getCost() != null) {
            dto.setCost(new FieldChangeInteger(notification.getCost().getOldField(), notification.getCost().getNewField()));
        }

        if (notification.getDuration() != null) {
            dto.setDuration(new FieldChangeInteger(notification.getDuration().getOldField(), notification.getDuration().getNewField()));
        }

        if (notification.getLocationId() != null) {
            dto.setLocationId(new FieldChangeLong(notification.getLocationId().getOldField(), notification.getLocationId().getNewField()));
        }

        return dto;
    }

    public List<NotificationDto> toDto(List<Notification> notificationList) {
        if (notificationList == null) return Collections.emptyList();
        return notificationList.stream().map(this::toDto).toList();
    }
}
