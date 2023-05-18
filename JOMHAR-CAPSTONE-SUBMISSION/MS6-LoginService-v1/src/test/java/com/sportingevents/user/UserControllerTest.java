package com.sportingevents.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sportingevents.common.constant.ApiEndpoint;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.servlet.http.HttpServletRequest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class UserControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private UserController userController;

    @Mock
    private UserServiceImpl userService;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver()).build();
        when(userService.validateToken(any(HttpServletRequest.class))).thenReturn(true);
        when(userService.saveUser(any(UserRequestModel.class))).thenReturn(UserMessage.USER_SAVE_SUCCESS);
        when(userService.getEmail(anyString())).thenReturn("email@email.com");
    }

    @Test
    public void givenValidJwtToken_returnTrue() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(ApiEndpoint.API + ApiEndpoint.VERSION + ApiEndpoint.USERS + ApiEndpoint.VALIDATE_JWT_TOKEN))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    public void givenValidUser_saveUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(ApiEndpoint.API + ApiEndpoint.VERSION + ApiEndpoint.USERS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(getUserRequestModel())))
                .andExpect(status().is(HttpStatus.CREATED.value()))
                .andExpect(content().string(UserMessage.USER_SAVE_SUCCESS));
    }

    @Test
    public void givenValidJwtToken_returnUserEmail() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(ApiEndpoint.API + ApiEndpoint.VERSION + ApiEndpoint.USERS + ApiEndpoint.GET_EMAIL_FROM_JWT)
                        .header(HttpHeaders.AUTHORIZATION, "test"))
                .andExpect(status().isOk())
                .andExpect(content().string("email@email.com"));
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

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
