package org.example.notificationmicroservice.controller;

import org.example.notificationmicroservice.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    NotificationService notificationService;
}
