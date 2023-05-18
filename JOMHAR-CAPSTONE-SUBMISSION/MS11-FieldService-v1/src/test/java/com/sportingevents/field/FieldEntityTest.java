package com.sportingevents.field;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FieldEntityTest {

    @Test
    public void whenFieldInitialized_returnField() {
        FieldEntity expected = new FieldEntity();
        expected.setFieldName("test");
        expected.setFieldId(1);
        expected.setCapacity(100);
        expected.setFieldAddress("test");
        expected.setActive(true);

        FieldEntity actual = getField();
        Assert.assertEquals(expected.getFieldId(), actual.getFieldId());
        Assert.assertEquals(expected.getFieldName(), actual.getFieldName());
        Assert.assertEquals(expected.getCapacity(), actual.getCapacity());
        Assert.assertEquals(expected.getFieldAddress(), actual.getFieldAddress());
        Assert.assertEquals(expected.getActive(), actual.getActive());
    }

    @Test(expected = NullPointerException.class)
    public void givenNullFieldId_whenInitialized_returnException() {
        FieldEntity field = new FieldEntity();
        field.setFieldId(null);
    }

    @Test(expected = NullPointerException.class)
    public void givenNullFieldName_whenInitialized_returnException() {
        FieldEntity field = new FieldEntity();
        field.setFieldName(null);
    }

    @Test(expected = NullPointerException.class)
    public void givenNullCapacity_whenInitialized_returnException() {
        FieldEntity field = new FieldEntity();
        field.setCapacity(null);
    }

    @Test(expected = NullPointerException.class)
    public void givenNullFieldAddress_whenInitialized_returnException() {
        FieldEntity field = new FieldEntity();
        field.setFieldAddress(null);
    }

    @Test(expected = NullPointerException.class)
    public void givenNullActive_whenInitialized_returnException() {
        FieldEntity field = new FieldEntity();
        field.setActive(null);
    }

    public FieldEntity getField() {
        FieldEntity field = new FieldEntity();
        field.setFieldName("test");
        field.setFieldId(1);
        field.setCapacity(100);
        field.setFieldAddress("test");
        field.setActive(true);
        return field;
    }
}
