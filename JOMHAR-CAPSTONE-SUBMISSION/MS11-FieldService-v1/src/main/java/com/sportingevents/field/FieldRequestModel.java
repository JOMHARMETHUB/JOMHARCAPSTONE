package com.sportingevents.field;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor
@Data
public class FieldRequestModel {

    @NonNull
    private String fieldName;

    @NonNull
    private String fieldAddress;

    @NonNull
    private Integer capacity;
}
