package org.example.notificationmicroservice.repository;

import org.example.notificationmicroservice.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface INotificationRepository extends JpaRepository<Notification, Integer> {
}
