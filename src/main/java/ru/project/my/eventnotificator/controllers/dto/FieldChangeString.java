package ru.project.my.eventnotificator.controllers.dto;

public class FieldChangeString {
    private String oldField;
    private String newField;

    public FieldChangeString() {}

    public FieldChangeString(String oldField, String newField) {
        this.oldField = oldField;
        this.newField = newField;
    }

    public String getOldField() {
        return oldField;
    }
    public void setOldField(String oldField) {
        this.oldField = oldField;
    }

    public String getNewField() {
        return newField;
    }
    public void setNewField(String newField) {
        this.newField = newField;
    }
}
