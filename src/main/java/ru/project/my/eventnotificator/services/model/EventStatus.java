package ru.project.my.eventnotificator.services.model;

public enum EventStatus {
    WAIT_START("Ожидает начала"),
    STARTED("Началось"),
    CANCELLED("Отменено"),
    FINISHED("Закончилось");

    private String name;

    EventStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
