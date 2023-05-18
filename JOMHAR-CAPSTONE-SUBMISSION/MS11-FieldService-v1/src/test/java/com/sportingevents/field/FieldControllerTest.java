package com.sportingevents.field;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sportingevents.common.constant.ApiEndpoint;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.ws.rs.core.MediaType;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class FieldControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private FieldController fieldController;

    @Mock
    private FieldServiceImpl fieldService;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(fieldController).setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver()).build();
    }

    @Test
    public void givenAuthenticatedUser_whenGetAllFields_returnFields() throws Exception {
        when(fieldService.getAllFields(any(Pageable.class))).thenReturn(getFields());
        mockMvc.perform(MockMvcRequestBuilders.get(ApiEndpoint.API + ApiEndpoint.VERSION + ApiEndpoint.FIELDS))
                .andExpect(status().isOk())
                .andExpect(content().string(asJsonString(getFields())));
    }

    @Test
    public void givenAuthenticatedUser_whenGetField_returnField() throws Exception {
        when(fieldService.getField(anyInt())).thenReturn(getField());
        mockMvc.perform(MockMvcRequestBuilders.get(ApiEndpoint.API + ApiEndpoint.VERSION + ApiEndpoint.FIELDS + ApiEndpoint.ID_PATH_VARIABLE, "1"))
                .andExpect(status().isOk())
                .andExpect(content().string(asJsonString(getField())));
    }

    @Test
    public void givenAuthenticatedUser_whenSaveField_returnMessage() throws Exception {
        when(fieldService.saveField(any(FieldRequestModel.class))).thenReturn(FieldMessage.FIELD_SAVE_SUCCESS);
        mockMvc.perform(MockMvcRequestBuilders.post(ApiEndpoint.API + ApiEndpoint.VERSION + ApiEndpoint.FIELDS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(getFieldRequestModel())))
                .andExpect(status().is(HttpStatus.CREATED.value()))
                .andExpect(content().string(FieldMessage.FIELD_SAVE_SUCCESS));
    }

    @Test
    public void givenAuthenticatedUser_whenUpdateField_returnMessage() throws Exception {
        when(fieldService.updateField(anyInt(), any(FieldRequestModel.class))).thenReturn(FieldMessage.FIELD_UPDATE_SUCCESS);
        mockMvc.perform(MockMvcRequestBuilders.put(ApiEndpoint.API + ApiEndpoint.VERSION + ApiEndpoint.FIELDS + ApiEndpoint.ID_PATH_VARIABLE, "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(getFieldRequestModel())))
                .andExpect(status().isOk())
                .andExpect(content().string(FieldMessage.FIELD_UPDATE_SUCCESS));
    }

    @Test
    public void givenAuthenticatedUser_whenDeleteField_returnMessage() throws Exception {
        when(fieldService.deleteField(anyInt())).thenReturn(FieldMessage.FIELD_DELETE_SUCCESS);
        mockMvc.perform(MockMvcRequestBuilders.delete(ApiEndpoint.API + ApiEndpoint.VERSION + ApiEndpoint.FIELDS + ApiEndpoint.ID_PATH_VARIABLE, "1"))
                .andExpect(status().isOk())
                .andExpect(content().string(FieldMessage.FIELD_DELETE_SUCCESS));
    }

    private FieldEntity getField() {
        FieldEntity field = new FieldEntity();
        field.setFieldId(1);
        field.setFieldAddress("test");
        field.setFieldName("test");
        field.setActive(true);
        field.setCapacity(100);
        return field;
    }

    private FieldRequestModel getFieldRequestModel() {
        FieldRequestModel field = new FieldRequestModel();
        field.setCapacity(100);
        field.setFieldAddress("test");
        field.setFieldName("test");
        return field;
    }

    private List<FieldEntity> getFields() {
        List<FieldEntity> fields = new ArrayList<>();
        for(int x=0;x<2;x++) {
            FieldEntity field = new FieldEntity();
            field.setFieldId(x+1);
            field.setFieldAddress("test");
            field.setFieldName("test");
            field.setActive(true);
            field.setCapacity(100);
            fields.add(field);
        }
        return fields;
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
