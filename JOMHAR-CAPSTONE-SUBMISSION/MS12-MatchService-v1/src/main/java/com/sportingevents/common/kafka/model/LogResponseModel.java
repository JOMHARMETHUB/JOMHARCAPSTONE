package com.sportingevents.common.kafka.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LogResponseModel {

    private String serviceName;

    private String uri;

    private String method;

    private String email;
}
