package com.sportingevents.field;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FieldService {
    List<FieldEntity> getAllFields(Pageable pageable);

    FieldEntity getField(Integer fieldId);

    String saveField(FieldRequestModel fieldRequestModel);

    String updateField(Integer fieldId, FieldRequestModel fieldRequestModel);

    String deleteField(Integer fieldId);
}
