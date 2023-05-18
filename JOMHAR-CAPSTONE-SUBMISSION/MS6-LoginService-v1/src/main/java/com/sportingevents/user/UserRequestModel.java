package com.sportingevents.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class UserRequestModel {

    @NonNull
    @Email
    @NotBlank
    private String email;

    @NonNull
    @NotBlank
    private String password;

    @NonNull
    @NotBlank
    private String phoneNumber;

    @NonNull
    @NotBlank
    private String firstName;

    @NonNull
    @NotBlank
    private String middleName;

    @NonNull
    @NotBlank
    private String lastName;
}
