package ru.project.my.eventnotificator.controllers.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class NotificationDto {
    private Long eventId;
    private FieldChangeString name;
    private FieldChangeInteger maxPlaces;
    private FieldChangeDateTime date;
    private FieldChangeInteger cost;
    private FieldChangeInteger duration;
    private FieldChangeLong locationId;

    public Long getEventId() {
        return eventId;
    }
    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public FieldChangeString getName() {
        return name;
    }
    public void setName(FieldChangeString name) {
        this.name = name;
    }

    public FieldChangeInteger getMaxPlaces() {
        return maxPlaces;
    }
    public void setMaxPlaces(FieldChangeInteger maxPlaces) {
        this.maxPlaces = maxPlaces;
    }

    public FieldChangeDateTime getDate() {
        return date;
    }
    public void setDate(FieldChangeDateTime date) {
        this.date = date;
    }

    public FieldChangeInteger getCost() {
        return cost;
    }
    public void setCost(FieldChangeInteger cost) {
        this.cost = cost;
    }

    public FieldChangeInteger getDuration() {
        return duration;
    }
    public void setDuration(FieldChangeInteger duration) {
        this.duration = duration;
    }

    public FieldChangeLong getLocationId() {
        return locationId;
    }
    public void setLocationId(FieldChangeLong locationId) {
        this.locationId = locationId;
    }
}
