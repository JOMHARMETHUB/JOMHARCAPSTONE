package com.sportingevents.user;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserEntityTest {

    @Test
    public void whenUserInitialized_returnUser() {
        UserEntity expected = new UserEntity();
        expected.setFirstName("test");
        expected.setMiddleName("test");
        expected.setLastName("test");
        expected.setEmail("test123@mail.com");
        expected.setPassword("@Dmin1234");
        expected.setUserId(1);
        expected.setPhoneNumber("12345678");

        UserEntity actual = getUser();

        Assert.assertEquals(expected.getUserId(), actual.getUserId());
        Assert.assertEquals(expected.getPassword(), actual.getPassword());
        Assert.assertEquals(expected.getEmail(), actual.getEmail());
        Assert.assertEquals(expected.getPhoneNumber(), actual.getPhoneNumber());
        Assert.assertEquals(expected.getFirstName(), actual.getFirstName());
        Assert.assertEquals(expected.getMiddleName(), actual.getMiddleName());
        Assert.assertEquals(expected.getLastName(), actual.getLastName());
    }

    @Test(expected = NullPointerException.class)
    public void givenNullUserId_whenInitialized_returnException() {
        UserEntity user = new UserEntity();
        user.setUserId(null);
    }

    @Test(expected = NullPointerException.class)
    public void givenNullPassword_whenInitialized_returnException() {
        UserEntity user = new UserEntity();
        user.setPassword(null);
    }

    @Test(expected = NullPointerException.class)
    public void givenNullEmail_whenInitialized_returnException() {
        UserEntity user = new UserEntity();
        user.setEmail(null);
    }

    @Test(expected = NullPointerException.class)
    public void givenNullPhoneNumber_whenInitialized_returnException() {
        UserEntity user = new UserEntity();
        user.setPhoneNumber(null);
    }

    @Test(expected = NullPointerException.class)
    public void givenNullFirstName_whenInitialized_returnException() {
        UserEntity user = new UserEntity();
        user.setFirstName(null);
    }

    @Test(expected = NullPointerException.class)
    public void givenNullMiddleName_whenInitialized_returnException() {
        UserEntity user = new UserEntity();
        user.setMiddleName(null);
    }

    @Test(expected = NullPointerException.class)
    public void givenNullLastName_whenInitialized_returnException() {
        UserEntity user = new UserEntity();
        user.setLastName(null);
    }

    private UserEntity getUser() {
        UserEntity user = new UserEntity();
        user.setFirstName("test");
        user.setMiddleName("test");
        user.setLastName("test");
        user.setEmail("test123@mail.com");
        user.setPassword("@Dmin1234");
        user.setUserId(1);
        user.setPhoneNumber("12345678");
        return user;
    }
}
