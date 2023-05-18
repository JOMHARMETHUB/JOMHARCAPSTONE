package com.sportingevents.log;

import com.sportingevents.kafka.model.LogResponseModel;

public interface LogService {
    void saveLog(LogResponseModel logResponseModel);
}
