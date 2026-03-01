package ru.project.my.eventnotificator.controllers.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NotificationReadDto {
    List<Long> notificationIds;

    public List<Long> getNotificationIds() {
        return notificationIds;
    }
    public void setNotificationIds(List<Long> notificationIds) {
        this.notificationIds = notificationIds;
    }
}
