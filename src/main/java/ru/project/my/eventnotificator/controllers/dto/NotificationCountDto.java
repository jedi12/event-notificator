package ru.project.my.eventnotificator.controllers.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class NotificationCountDto {
    private Long count;

    public NotificationCountDto() {}

    public NotificationCountDto(Long count) {
        this.count = count;
    }

    public Long getCount() {
        return count;
    }
    public void setCount(Long count) {
        this.count = count;
    }
}
