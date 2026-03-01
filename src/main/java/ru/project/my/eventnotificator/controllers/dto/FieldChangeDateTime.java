package ru.project.my.eventnotificator.controllers.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class FieldChangeDateTime {
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime oldField;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime newField;

    public FieldChangeDateTime() {}

    public FieldChangeDateTime(LocalDateTime oldField, LocalDateTime newField) {
        this.oldField = oldField;
        this.newField = newField;
    }

    public LocalDateTime getOldField() {
        return oldField;
    }
    public void setOldField(LocalDateTime oldField) {
        this.oldField = oldField;
    }

    public LocalDateTime getNewField() {
        return newField;
    }
    public void setNewField(LocalDateTime newField) {
        this.newField = newField;
    }
}
