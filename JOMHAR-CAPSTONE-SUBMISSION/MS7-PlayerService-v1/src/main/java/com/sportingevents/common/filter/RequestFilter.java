package com.sportingevents.common.filter;

import com.sportingevents.common.constant.AppConstant;
import com.sportingevents.common.jwt.JwtRestService;
import com.sportingevents.common.kafka.KafkaProducer;
import com.sportingevents.common.kafka.model.LogResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Component
public class RequestFilter extends OncePerRequestFilter {

    @Autowired
    private KafkaProducer kafkaProducer;

    @Autowired
    private JwtRestService jwtRestService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String tokenHeader = request.getHeader("Authorization");
        if(tokenHeader != null && tokenHeader.startsWith("Bearer ")) {
            Boolean valid = jwtRestService.validateJwtToken(tokenHeader).getBody();
            Optional<Boolean> validOptional = Optional.ofNullable(valid);
            if(validOptional.isPresent()) {
                boolean isValid = validOptional.get();
                if(isValid) {
                    String email = jwtRestService.getEmailFromJwt(tokenHeader).getBody();
                    LogResponseModel logModel = new LogResponseModel();
                    logModel.setEmail(email);
                    logModel.setUri(request.getRequestURI());
                    logModel.setServiceName(AppConstant.SERVICE_NAME);
                    logModel.setMethod(request.getMethod());
                    kafkaProducer.sendMessage(logModel);
                    filterChain.doFilter(request, response);

                }
            }
        }
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
    }


}
