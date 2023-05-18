package com.sportingevents.user;

import com.sportingevents.common.util.NameUtil;
import com.sportingevents.common.util.NumberUtil;
import com.sportingevents.common.util.PasswordUtil;
import com.sportingevents.config.jwt.JwtUserDetailsService;
import com.sportingevents.config.jwt.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenManager tokenManager;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Override
    public String saveUser(UserRequestModel userRequestModel) {
        Optional<UserEntity> checkByEmail = userRepository.findByEmail(userRequestModel.getEmail());
        if(checkByEmail.isPresent())
            throw new UserException(UserMessage.USER_EMAIL_EXIST);
        Optional<UserEntity> checkByPhoneNumber = userRepository.findByPhoneNumber(userRequestModel.getPhoneNumber());
        if(checkByPhoneNumber.isPresent())
            throw new UserException(UserMessage.USER_PHONE_EXIST);
        if(!PasswordUtil.isValidPassword(userRequestModel.getPassword()))
            throw new UserException(UserMessage.USER_PASSWORD_INVALID);
        if(!NameUtil.isValidName(userRequestModel.getFirstName()) ||
                !NameUtil.isValidName(userRequestModel.getMiddleName()) || !NameUtil.isValidName(userRequestModel.getLastName()))
            throw new UserException(UserMessage.USER_INVALID_NAME);
        if(!NumberUtil.isValidNumber(userRequestModel.getPhoneNumber()))
            throw new UserException((UserMessage.USER_INVALID_PHONE_NUMBER));
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(userRequestModel.getEmail());
        userEntity.setPassword(passwordEncoder.encode(userRequestModel.getPassword()));
        userEntity.setPhoneNumber(userRequestModel.getPhoneNumber());
        userEntity.setFirstName(userRequestModel.getFirstName().toUpperCase());
        userEntity.setMiddleName(userRequestModel.getMiddleName().toUpperCase());
        userEntity.setLastName(userRequestModel.getLastName().toUpperCase());

        userRepository.save(userEntity);

        return UserMessage.USER_SAVE_SUCCESS;
    }

    @Override
    public Boolean validateToken(HttpServletRequest request) {
        try {
            String tokenHeader = request.getHeader("Authorization");
            if (tokenHeader != null && tokenHeader.startsWith("Bearer ")) {
                String token = tokenHeader.substring(7);
                return tokenManager.validateJwtToken(token, jwtUserDetailsService.loadUserByUsername(tokenManager.getUsernameFromToken(token)));
            } else {
                return false;
            }
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public String getEmail(String token) {
        token = token.substring(7);
        return tokenManager.getUsernameFromToken(token);
    }

}
