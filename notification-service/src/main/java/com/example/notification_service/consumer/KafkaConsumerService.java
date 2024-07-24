package com.example.notification_service.consumer;


import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaConsumerService {





    @KafkaListener(topics = "new-account-topic", groupId = "application-service-group")
    public void receiveMessage(String message) {

        System.out.println("Received message: " + message + "  account no was created");


    }

    @KafkaListener(topics = "account-deletion-topic", groupId = "notification-group")
    public void receiveAccountDeletionMessage(String message) {

        System.out.println("Received message: " + message + " - Account was deleted");
    }

    @KafkaListener(topics = "login-topic", groupId = "notification-group")
    public void listenLoginTopic(String message) {
        // Mesajı işleyin
        System.out.println("Received message from login-topic: " + message);
    }

    @KafkaListener(topics = "register-topic", groupId = "notification-group")
    public void listenRegisterTopic(String message) {
        // Mesajı işleyin
        System.out.println("Received message from register-topic: " + message);
    }



}