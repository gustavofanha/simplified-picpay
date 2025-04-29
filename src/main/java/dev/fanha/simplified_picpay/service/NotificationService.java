package dev.fanha.simplified_picpay.service;

import dev.fanha.simplified_picpay.client.NotificationClient;
import dev.fanha.simplified_picpay.entity.Transaction;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final NotificationClient notificationClient;

    public NotificationService(NotificationClient notificationClient) {
        this.notificationClient = notificationClient;
    }

    public void sendNotification(Transaction transaction) {
        notificationClient.sendNotification(transaction);
    }
}
