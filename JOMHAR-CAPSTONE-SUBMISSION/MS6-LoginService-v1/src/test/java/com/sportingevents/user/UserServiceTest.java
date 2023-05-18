package com.sportingevents.user;

import com.sportingevents.config.jwt.JwtUserDetailsService;
import com.sportingevents.config.jwt.TokenManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.http.HttpServletRequest;


import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private TokenManager tokenManager;

    @Mock
    private JwtUserDetailsService jwtUserDetailsService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private UserDetails userDetails;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private String jwtToken = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VydGVzdEBtYWlsLmNvbSIsImV4cCI6MTY4MjQ0NTEzOSwiaWF0IjoxNjgyNDA5MTM5fQ.pvuJvDMkZvr2z0ZhE25HHt0OmBDjw1d3RvRVCwZUMiJJLJYmMul8YT9JGasGVNKE63Cl5WHTOHO45skDhj6FCw";

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void givenJwtToken_whenValidToken_returnTrue() {
        when(request.getHeader("Authorization")).thenReturn(jwtToken);
        when(tokenManager.getUsernameFromToken(anyString())).thenReturn("test@mail.com");
        when(tokenManager.validateJwtToken(anyString(), any(UserDetails.class))).thenReturn(true);
        when(jwtUserDetailsService.loadUserByUsername(anyString())).thenReturn(userDetails);
        when(passwordEncoder.encode(anyString())).thenReturn("testencodedpassword");
        Boolean result = userService.validateToken(request);
        Assert.assertTrue(result);
    }

    @Test
    public void givenJwtToken_whenInvalidToken_returnFalse() {
        when(request.getHeader("Authorization")).thenReturn(jwtToken);
        when(tokenManager.getUsernameFromToken(anyString())).thenReturn("test@mail.com");
        when(tokenManager.validateJwtToken(anyString(), any(UserDetails.class))).thenReturn(false);
        when(jwtUserDetailsService.loadUserByUsername(anyString())).thenReturn(userDetails);
        Boolean result = userService.validateToken(request);
        Assert.assertFalse(result);
    }

    @Test
    public void givenJwtToken_whenExceptionThrown_returnFalse() {
        when(request.getHeader("Authorization")).thenThrow(NullPointerException.class);
        Boolean result = userService.validateToken(request);
        Assert.assertFalse(result);
    }

    @Test
    public void givenJwtToken_whenNoBearer_returnFalse() {
        when(request.getHeader("Authorization")).thenReturn("testasdas");
        Boolean result = userService.validateToken(request);
        Assert.assertFalse(result);
    }

    @Test
    public void givenJwtToken_whenHeaderIsNull_returnFalse() {
        when(request.getHeader("Authorization")).thenReturn(null);
        Boolean result = userService.validateToken(request);
        Assert.assertFalse(result);
    }

    @Test
    public void givenExistingUser_returnErrorMessage() {
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(getUserEntity()));
        UserRequestModel userRequestModel = getUserRequestModel();
        exception.expect(UserException.class);
        exception.expectMessage(UserMessage.USER_EMAIL_EXIST);
        userService.saveUser(userRequestModel);
    }

    @Test
    public void givenExistingPhoneNumber_returnErrorMessage() {
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(userRepository.findByPhoneNumber(anyString())).thenReturn(Optional.of(getUserEntity()));
        UserRequestModel userRequestModel = getUserRequestModel();
        exception.expect(UserException.class);
        exception.expectMessage(UserMessage.USER_PHONE_EXIST);
        userService.saveUser(userRequestModel);
    }

    @Test
    public void givenInvalidPassword_returnErrorMessage() {
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(userRepository.findByPhoneNumber(anyString())).thenReturn(Optional.empty());
        UserRequestModel userRequestModel = getUserRequestModel();
        userRequestModel.setPassword("ad1234");
        exception.expect(UserException.class);
        exception.expectMessage(UserMessage.USER_PASSWORD_INVALID);
        userService.saveUser(userRequestModel);
    }

    @Test
    public void givenInvalidFirstName_returnErrorMessage() {
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(userRepository.findByPhoneNumber(anyString())).thenReturn(Optional.empty());
        UserRequestModel userRequestModel = getUserRequestModel();
        userRequestModel.setFirstName("test123");
        exception.expect(UserException.class);
        exception.expectMessage(UserMessage.USER_INVALID_NAME);
        userService.saveUser(userRequestModel);
    }

    @Test
    public void givenInvalidMiddleName_returnErrorMessage() {
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(userRepository.findByPhoneNumber(anyString())).thenReturn(Optional.empty());
        UserRequestModel userRequestModel = getUserRequestModel();
        userRequestModel.setMiddleName("test123");
        exception.expect(UserException.class);
        exception.expectMessage(UserMessage.USER_INVALID_NAME);
        userService.saveUser(userRequestModel);
    }

    @Test
    public void givenInvalidLastName_returnErrorMessage() {
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(userRepository.findByPhoneNumber(anyString())).thenReturn(Optional.empty());
        UserRequestModel userRequestModel = getUserRequestModel();
        userRequestModel.setLastName("test123");
        exception.expect(UserException.class);
        exception.expectMessage(UserMessage.USER_INVALID_NAME);
        userService.saveUser(userRequestModel);
    }

    @Test
    public void givenInvalidPhoneNumber_returnErrorMessage() {
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(userRepository.findByPhoneNumber(anyString())).thenReturn(Optional.empty());
        UserRequestModel userRequestModel = getUserRequestModel();
        userRequestModel.setPhoneNumber("abc");
        exception.expect(UserException.class);
        exception.expectMessage(UserMessage.USER_INVALID_PHONE_NUMBER);
        userService.saveUser(userRequestModel);
    }

    @Test
    public void givenUser_whenValid_thenSaveAndReturnMessage() {;
        when(passwordEncoder.encode(anyString())).thenReturn("testencodedpassword");
        UserRequestModel userRequestModel = getUserRequestModel();
        String result = userService.saveUser(userRequestModel);
        Assert.assertEquals(UserMessage.USER_SAVE_SUCCESS, result);
    }

    @Test
    public void givenValidToken_returnEmail() {
        when(tokenManager.getUsernameFromToken(anyString())).thenReturn("email@email.com");
        String result = userService.getEmail("Bearer testasdjlkasjd");
        Assert.assertNotNull(result);
    }

    private UserRequestModel getUserRequestModel() {
        UserRequestModel userRequestModel = new UserRequestModel();
        userRequestModel.setEmail("test@mail.com");
        userRequestModel.setPassword("@Dmin1234");
        userRequestModel.setFirstName("testFN");
        userRequestModel.setLastName("testLN");
        userRequestModel.setPhoneNumber("09928374829");
        userRequestModel.setMiddleName("testMN");
        return userRequestModel;
    }

    private UserEntity getUserEntity() {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("test@mail.com");
        userEntity.setPassword("@Dmin1234");
        userEntity.setFirstName("testFN");
        userEntity.setLastName("testLN");
        userEntity.setPhoneNumber("09928374829");
        userEntity.setMiddleName("testMN");
        return userEntity;
    }
}
