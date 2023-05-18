package com.sportingevents.field;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "fields", schema = "sporting_events_field_schema")
@NoArgsConstructor
public class FieldEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    @Column(name = "field_id")
    private Integer fieldId;

    @NonNull
    @Column(name = "field_name")
    private String fieldName;

    @NonNull
    @Column(name = "field_address")
    private String fieldAddress;

    @NonNull
    @Column(name = "capacity")
    private Integer capacity;

    @NonNull
    @Column(name = "active")
    private Boolean active = true;
}
