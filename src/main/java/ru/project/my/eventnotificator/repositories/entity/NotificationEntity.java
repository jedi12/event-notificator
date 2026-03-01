package ru.project.my.eventnotificator.repositories.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import ru.project.my.eventnotificator.services.model.EventStatus;

import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
public class NotificationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "event_id")
    private Long eventId;

    @Column(name = "changed_by_user_id")
    private Long changedByUserId;

    @Column(name = "ovner_id")
    private Long ownerId;

    @Column(name = "reg_user_id")
    private Long regUserId;

    @Column(name = "old_name")
    private String oldName;

    @Column(name = "new_name")
    private String newName;

    @Column(name = "old_max_places")
    private Integer oldMaxPlaces;

    @Column(name = "new_max_places")
    private Integer newMaxPlaces;

    @Column(name = "old_occupied_places")
    private Integer oldOccupiedPlaces;

    @Column(name = "new_occupied_places")
    private Integer newOccupiedPlaces;

    @Column(name = "old_date")
    private LocalDateTime oldDate;

    @Column(name = "new_date")
    private LocalDateTime newDate;

    @Column(name = "old_cost")
    private Integer oldCost;

    @Column(name = "new_cost")
    private Integer newCost;

    @Column(name = "old_duration")
    private Integer oldDuration;

    @Column(name = "new_duration")
    private Integer newDuration;

    @Column(name = "old_location_id")
    private Long oldLocationId;

    @Column(name = "new_location_id")
    private Long newLocationId;

    @Column(name = "old_status")
    private EventStatus oldStatus;

    @Column(name = "new_status")
    private EventStatus newStatus;

    @Column(name = "read")
    private boolean read;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @PrePersist
    void perPersist() {
        createDate = LocalDateTime.now();
    }

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

    public Long getRegUserId() {
        return regUserId;
    }
    public void setRegUserId(Long regUserId) {
        this.regUserId = regUserId;
    }

    public String getOldName() {
        return oldName;
    }
    public void setOldName(String oldName) {
        this.oldName = oldName;
    }

    public String getNewName() {
        return newName;
    }
    public void setNewName(String newName) {
        this.newName = newName;
    }

    public Integer getOldMaxPlaces() {
        return oldMaxPlaces;
    }
    public void setOldMaxPlaces(Integer oldMaxPlaces) {
        this.oldMaxPlaces = oldMaxPlaces;
    }

    public Integer getNewMaxPlaces() {
        return newMaxPlaces;
    }
    public void setNewMaxPlaces(Integer newMaxPlaces) {
        this.newMaxPlaces = newMaxPlaces;
    }

    public Integer getOldOccupiedPlaces() {
        return oldOccupiedPlaces;
    }
    public void setOldOccupiedPlaces(Integer oldOccupiedPlaces) {
        this.oldOccupiedPlaces = oldOccupiedPlaces;
    }

    public Integer getNewOccupiedPlaces() {
        return newOccupiedPlaces;
    }
    public void setNewOccupiedPlaces(Integer newOccupiedPlaces) {
        this.newOccupiedPlaces = newOccupiedPlaces;
    }

    public LocalDateTime getOldDate() {
        return oldDate;
    }
    public void setOldDate(LocalDateTime oldDate) {
        this.oldDate = oldDate;
    }

    public LocalDateTime getNewDate() {
        return newDate;
    }
    public void setNewDate(LocalDateTime newDate) {
        this.newDate = newDate;
    }

    public Integer getOldCost() {
        return oldCost;
    }
    public void setOldCost(Integer oldCost) {
        this.oldCost = oldCost;
    }

    public Integer getNewCost() {
        return newCost;
    }
    public void setNewCost(Integer newCost) {
        this.newCost = newCost;
    }

    public Integer getOldDuration() {
        return oldDuration;
    }
    public void setOldDuration(Integer oldDuration) {
        this.oldDuration = oldDuration;
    }

    public Integer getNewDuration() {
        return newDuration;
    }
    public void setNewDuration(Integer newDuration) {
        this.newDuration = newDuration;
    }

    public Long getOldLocationId() {
        return oldLocationId;
    }
    public void setOldLocationId(Long oldLocationId) {
        this.oldLocationId = oldLocationId;
    }

    public Long getNewLocationId() {
        return newLocationId;
    }
    public void setNewLocationId(Long newLocationId) {
        this.newLocationId = newLocationId;
    }

    public EventStatus getOldStatus() {
        return oldStatus;
    }
    public void setOldStatus(EventStatus oldStatus) {
        this.oldStatus = oldStatus;
    }

    public EventStatus getNewStatus() {
        return newStatus;
    }
    public void setNewStatus(EventStatus newStatus) {
        this.newStatus = newStatus;
    }

    public boolean isRead() {
        return read;
    }
    public void setRead(boolean read) {
        this.read = read;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }
    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }
}
