package com.sportingevents.kafka;

import com.sportingevents.common.constant.GroupId;
import com.sportingevents.common.constant.TopicName;
import com.sportingevents.kafka.model.LogResponseModel;
import com.sportingevents.log.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @Autowired
    private LogService logService;
    @KafkaListener(topics = TopicName.LOG_TOPIC_NAME,
            groupId = GroupId.LOGGER_GROUP)
    public void consumePlayerHistory(@Payload LogResponseModel model) {
        logService.saveLog(model);
    }
}
