package com.sportingevents.log;

import com.sportingevents.kafka.model.LogResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class LogServiceImpl implements LogService{

    @Autowired
    private LogRepository logRepository;

    @Override
    public void saveLog(LogResponseModel logResponseModel) {

        logRepository.save(mapResponseModelToEntity(logResponseModel));
    }

    private LogEntity mapResponseModelToEntity(LogResponseModel logResponseModel)  {
        LogEntity log = new LogEntity();
        log.setEmail(logResponseModel.getEmail());
        log.setServiceName(logResponseModel.getServiceName());
        log.setUri(logResponseModel.getUri());
        log.setMethod(logResponseModel.getMethod());
        log.setDateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        return log;
    }
}
