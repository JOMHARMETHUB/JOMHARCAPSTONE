package com.sportingevents.common.kafka;

import com.sportingevents.common.kafka.model.LogResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {
    @Autowired
    private KafkaTemplate<String, LogResponseModel> kafkaTemplate;

    public void sendMessage(LogResponseModel model){
        kafkaTemplate.send(KafkaConstant.LOG_TOPIC_NAME, model);
    }
}
