package com.example.account_service.kafka.producer;


import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    private static final String TOPIC = "new-account-topic";

    public void sendNewAccountMessage(String accountId) {
        kafkaTemplate.send(TOPIC, accountId);
    }

    public void sendAccountDeletionMessage(String accountId) {

        kafkaTemplate.send("account-deletion-topic", accountId);
    }

}