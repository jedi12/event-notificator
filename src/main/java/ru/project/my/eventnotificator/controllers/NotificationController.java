package ru.project.my.eventnotificator.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.project.my.eventnotificator.controllers.dto.NotificationDto;
import ru.project.my.eventnotificator.controllers.dto.NotificationReadDto;
import ru.project.my.eventnotificator.converters.NotificationDtoConverter;
import ru.project.my.eventnotificator.services.NotificationService;
import ru.project.my.eventnotificator.services.model.Notification;

import java.util.List;

@RestController
public class NotificationController {
    private final NotificationService notificationService;
    private final NotificationDtoConverter dtoConverter;

    public NotificationController(NotificationService notificationService, NotificationDtoConverter dtoConverter) {
        this.notificationService = notificationService;
        this.dtoConverter = dtoConverter;
    }

    @GetMapping("/notifications")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<List<NotificationDto>> getUserNotifications() {
        List<Notification> notifications = notificationService.getUserNotifications();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(dtoConverter.toDto(notifications));
    }

    @PostMapping("/notifications")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<Void> markAsRead(@RequestBody NotificationReadDto notificationReadDto) {
        notificationService.markAsRead(notificationReadDto.getNotificationIds());

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT).build();
    }
}
