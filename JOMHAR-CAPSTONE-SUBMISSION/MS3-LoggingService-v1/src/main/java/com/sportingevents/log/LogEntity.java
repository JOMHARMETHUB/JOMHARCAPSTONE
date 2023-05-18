package com.sportingevents.log;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "logs", schema = "sporting_events_log_schema")
public class LogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    @Column(name = "log_id")
    private Integer logId;

    @NonNull
    @Column(name = "service_name")
    private String serviceName;

    @NonNull
    @Column(name = "uri")
    private String uri;

    @NonNull
    @Column(name = "email")
    private String email;

    @NonNull
    @Column(name = "method")
    private String method;

    @NonNull
    @Column(name = "date_time")
    private String dateTime;
}
