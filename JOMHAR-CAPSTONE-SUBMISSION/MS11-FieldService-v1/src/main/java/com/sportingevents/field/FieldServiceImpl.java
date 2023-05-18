package com.sportingevents.field;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FieldServiceImpl implements FieldService{

    @Autowired
    private FieldRepository fieldRepository;

    @Override
    public List<FieldEntity> getAllFields(Pageable pageable){
        List<FieldEntity> fieldEntityList = fieldRepository.findByActiveTrue(pageable).getContent();
        if(fieldEntityList.isEmpty())
            throw new FieldException(FieldMessage.FIELD_NOT_FOUND);
        return fieldEntityList;
    }

    @Override
    public FieldEntity getField(Integer fieldId){
        Optional<FieldEntity> fieldEntityOptional = fieldRepository.findByFieldIdAndActiveTrue(fieldId);
        if(!fieldEntityOptional.isPresent())
            throw new FieldException(FieldMessage.FIELD_NOT_FOUND);
        return fieldEntityOptional.get();
    }

    @Override
    public String saveField(FieldRequestModel fieldRequestModel){
        FieldEntity fieldEntity = new FieldEntity();
        fieldEntity.setFieldName(fieldRequestModel.getFieldName());
        fieldEntity.setFieldAddress(fieldRequestModel.getFieldAddress());
        fieldEntity.setCapacity(fieldRequestModel.getCapacity());
        fieldRepository.saveAndFlush(fieldEntity);
        return FieldMessage.FIELD_SAVE_SUCCESS;
    }

    @Override
    public String updateField(Integer fieldId, FieldRequestModel fieldRequestModel){
        Optional<FieldEntity> fieldEntityOptional = fieldRepository.findByFieldIdAndActiveTrue(fieldId);
        if(!fieldEntityOptional.isPresent())
            throw new FieldException(FieldMessage.FIELD_NOT_FOUND);
        FieldEntity fieldEntity = fieldEntityOptional.get();
        fieldEntity.setFieldName(fieldRequestModel.getFieldName());
        fieldEntity.setFieldAddress(fieldRequestModel.getFieldAddress());
        fieldEntity.setCapacity(fieldRequestModel.getCapacity());
        fieldRepository.saveAndFlush(fieldEntity);
        return FieldMessage.FIELD_UPDATE_SUCCESS;
    }

    @Override
    public String deleteField(Integer fieldId){
        Optional<FieldEntity> fieldEntityOptional = fieldRepository.findByFieldIdAndActiveTrue(fieldId);
        if(!fieldEntityOptional.isPresent())
            throw new FieldException(FieldMessage.FIELD_NOT_FOUND);
        FieldEntity fieldEntity = fieldEntityOptional.get();
        fieldEntity.setActive(false);
        fieldRepository.saveAndFlush(fieldEntity);
        return FieldMessage.FIELD_DELETE_SUCCESS;
    }
}
