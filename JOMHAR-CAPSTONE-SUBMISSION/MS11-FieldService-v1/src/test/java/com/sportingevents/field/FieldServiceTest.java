package com.sportingevents.field;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class FieldServiceTest {

    @InjectMocks
    private FieldServiceImpl fieldService;

    @Mock
    private FieldRepository fieldRepository;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void whenGetAllFields_returnFields() {
        Pageable page = PageRequest.of(0, 10);
        when(fieldRepository.findByActiveTrue(any(Pageable.class))).thenReturn(getPageFieldEntity());
        List<FieldEntity> result = fieldService.getAllFields(page);
        Assert.assertNotNull(result);
    }

    @Test
    public void whenGetAllFieldsEmpty_returnMessage() {
        exception.expect(FieldException.class);
        exception.expectMessage(FieldMessage.FIELD_NOT_FOUND);
        Pageable page = PageRequest.of(0, 10);
        when(fieldRepository.findByActiveTrue(any(Pageable.class))).thenReturn(Page.empty());
        fieldService.getAllFields(page);
    }

    @Test
    public void givenValidId_whenGetField_returnField() {
        when(fieldRepository.findByFieldIdAndActiveTrue(any(Integer.class))).thenReturn(Optional.of(getFieldEntity()));
        FieldEntity fieldEntity = fieldService.getField(1);
        Assert.assertNotNull(fieldEntity);
    }

    @Test
    public void givenFieldNotExisting_whenGetField_returnMessage() {
        exception.expect(FieldException.class);
        exception.expectMessage(FieldMessage.FIELD_NOT_FOUND);
        when(fieldRepository.findByFieldIdAndActiveTrue(any(Integer.class))).thenReturn(Optional.empty());
        fieldService.getField(1);
    }

    @Test
    public void givenValidRequestModel_whenSaveField_returnMessage() {
        FieldRequestModel field = getFieldRequestModel();
        String result = fieldService.saveField(field);
        Assert.assertEquals(FieldMessage.FIELD_SAVE_SUCCESS, result);
    }

    @Test
    public void givenValidRequestModelAndId_whenUpdateField_returnMessage() {
        FieldRequestModel field = getFieldRequestModel();
        when(fieldRepository.findByFieldIdAndActiveTrue(any(Integer.class))).thenReturn(Optional.of(getFieldEntity()));
        String result = fieldService.updateField(1, field);
        Assert.assertEquals(FieldMessage.FIELD_UPDATE_SUCCESS, result);
    }

    @Test
    public void givenFieldNotExisting_whenUpdateField_returnMessage() {
        exception.expect(FieldException.class);
        exception.expectMessage(FieldMessage.FIELD_NOT_FOUND);
        FieldRequestModel field = getFieldRequestModel();
        when(fieldRepository.findByFieldIdAndActiveTrue(any(Integer.class))).thenReturn(Optional.empty());
        fieldService.updateField(1, field);
    }

    @Test
    public void givenValidId_whenDeleteField_returnMessage() {
        when(fieldRepository.findByFieldIdAndActiveTrue(any(Integer.class))).thenReturn(Optional.of(getFieldEntity()));
        String result = fieldService.deleteField(1);
        Assert.assertEquals(FieldMessage.FIELD_DELETE_SUCCESS, result);
    }

    @Test
    public void givenFieldNotExisting_whenDeleteField_returnMessage() {
        exception.expect(FieldException.class);
        exception.expectMessage(FieldMessage.FIELD_NOT_FOUND);
        when(fieldRepository.findByFieldIdAndActiveTrue(any(Integer.class))).thenReturn(Optional.empty());
        fieldService.deleteField(1);
    }

    private FieldRequestModel getFieldRequestModel() {
        FieldRequestModel fieldRequestModel = new FieldRequestModel();
        fieldRequestModel.setFieldName("test");
        fieldRequestModel.setFieldAddress("test");
        fieldRequestModel.setCapacity(10);
        return fieldRequestModel;
    }

    private Page<FieldEntity> getPageFieldEntity() {
        Pageable pageable = PageRequest.of(0, 10);
        List<FieldEntity> fields = getFieldEntities();
        int start = Math.min((int)pageable.getOffset(), fields.size());
        int end = Math.min((start + pageable.getPageSize()), fields.size());
        Page<FieldEntity> fieldEntityPage = new PageImpl<>(fields.subList(start, end), pageable, fields.size());
        return fieldEntityPage;
    }

    private List<FieldEntity> getFieldEntities() {
        List<FieldEntity> fields = new ArrayList<>();
        for(int x=0;x<2;x++){
            FieldEntity field = getFieldEntity();
            field.setFieldId(x+1);
            fields.add(field);
        }
        return fields;
    }


    private FieldEntity getFieldEntity() {
        FieldEntity fieldEntity = new FieldEntity();
        fieldEntity.setFieldAddress("test");
        fieldEntity.setActive(true);
        fieldEntity.setFieldId(1);
        fieldEntity.setCapacity(10);
        fieldEntity.setFieldName("test");
        return fieldEntity;
    }
}
