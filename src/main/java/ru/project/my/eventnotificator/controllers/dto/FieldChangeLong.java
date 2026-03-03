package ru.project.my.eventnotificator.controllers.dto;

public class FieldChangeLong {
    private Long oldField;
    private Long newField;

    public FieldChangeLong() {}

    public FieldChangeLong(Long oldField, Long newField) {
        this.oldField = oldField;
        this.newField = newField;
    }

    public Long getOldField() {
        return oldField;
    }
    public void setOldField(Long oldField) {
        this.oldField = oldField;
    }

    public Long getNewField() {
        return newField;
    }
    public void setNewField(Long newField) {
        this.newField = newField;
    }
}
