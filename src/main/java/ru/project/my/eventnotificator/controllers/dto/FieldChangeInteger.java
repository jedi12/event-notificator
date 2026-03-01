package ru.project.my.eventnotificator.controllers.dto;

public class FieldChangeInteger {
    private Integer oldField;
    private Integer newField;

    public FieldChangeInteger() {}

    public FieldChangeInteger(Integer oldField, Integer newField) {
        this.oldField = oldField;
        this.newField = newField;
    }

    public Integer getOldField() {
        return oldField;
    }
    public void setOldField(Integer oldField) {
        this.oldField = oldField;
    }

    public Integer getNewField() {
        return newField;
    }
    public void setNewField(Integer newField) {
        this.newField = newField;
    }
}
