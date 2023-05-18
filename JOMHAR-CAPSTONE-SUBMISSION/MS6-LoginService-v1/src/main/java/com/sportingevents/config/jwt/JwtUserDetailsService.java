package com.sportingevents.config.jwt;

import com.sportingevents.user.UserEntity;
import com.sportingevents.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> userEntityOptional = userRepository.findByEmail(username);
        if(!userEntityOptional.isPresent())
            throw new UsernameNotFoundException("User not existing");
        return new User(userEntityOptional.get().getEmail(), userEntityOptional.get().getPassword(), new ArrayList<>());
    }
}
