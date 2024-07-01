package org.example.notificationmicroservice.service;

import org.example.notificationmicroservice.repository.INotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    INotificationRepository notificationRepository;
}
