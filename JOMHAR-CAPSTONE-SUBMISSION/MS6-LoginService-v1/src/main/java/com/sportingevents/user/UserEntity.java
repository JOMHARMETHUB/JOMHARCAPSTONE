package com.sportingevents.user;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "users", schema = "sporting_events_user_schema")
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    @NonNull
    private Integer userId;

    @Column(name = "email")
    @NonNull
    private String email;

    @Column(name = "password")
    @NonNull
    private String password;

    @Column(name = "phone_number")
    @NonNull
    private String phoneNumber;

    @Column(name = "first_name")
    @NonNull
    private String firstName;


    @Column(name = "middle_name")
    @NonNull
    private String middleName;

    @Column(name = "last_name")
    @NonNull
    private String lastName;

}
