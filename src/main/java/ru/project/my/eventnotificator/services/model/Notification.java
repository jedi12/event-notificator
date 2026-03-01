package ru.project.my.eventnotificator.services.model;

import ru.project.my.eventnotificator.kafka.model.FieldChange;

import java.time.LocalDateTime;

public class Notification {
    private Long id;
    private Long eventId;
    private Long changedByUserId;
    private Long ownerId;

    private FieldChange<String> name;
    private FieldChange<Integer> maxPlaces;
    private FieldChange<Integer> occupiedPlaces;
    private FieldChange<LocalDateTime> date;
    private FieldChange<Integer> cost;
    private FieldChange<Integer> duration;
    private FieldChange<Long> locationId;
    private FieldChange<EventStatus> status;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getEventId() {
        return eventId;
    }
    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public Long getChangedByUserId() {
        return changedByUserId;
    }
    public void setChangedByUserId(Long changedByUserId) {
        this.changedByUserId = changedByUserId;
    }

    public Long getOwnerId() {
        return ownerId;
    }
    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public FieldChange<String> getName() {
        return name;
    }
    public void setName(FieldChange<String> name) {
        this.name = name;
    }

    public FieldChange<Integer> getMaxPlaces() {
        return maxPlaces;
    }
    public void setMaxPlaces(FieldChange<Integer> maxPlaces) {
        this.maxPlaces = maxPlaces;
    }

    public FieldChange<Integer> getOccupiedPlaces() {
        return occupiedPlaces;
    }
    public void setOccupiedPlaces(FieldChange<Integer> occupiedPlaces) {
        this.occupiedPlaces = occupiedPlaces;
    }

    public FieldChange<LocalDateTime> getDate() {
        return date;
    }
    public void setDate(FieldChange<LocalDateTime> date) {
        this.date = date;
    }

    public FieldChange<Integer> getCost() {
        return cost;
    }
    public void setCost(FieldChange<Integer> cost) {
        this.cost = cost;
    }

    public FieldChange<Integer> getDuration() {
        return duration;
    }
    public void setDuration(FieldChange<Integer> duration) {
        this.duration = duration;
    }

    public FieldChange<Long> getLocationId() {
        return locationId;
    }
    public void setLocationId(FieldChange<Long> locationId) {
        this.locationId = locationId;
    }

    public FieldChange<EventStatus> getStatus() {
        return status;
    }
    public void setStatus(FieldChange<EventStatus> status) {
        this.status = status;
    }
}