package com.sportingevents.field;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;


@Data
@NoArgsConstructor
public class FieldResponseModel {


    @NonNull
    private Integer fieldId;

    @NonNull
    private String fieldName;

    @NonNull
    private String fieldAddress;

    @NonNull
    private Integer capacity;

    @NonNull
    private Boolean active;
}
