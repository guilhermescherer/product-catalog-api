package com.personal.productcatalog.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.personal.productcatalog.model.User;
import org.springframework.stereotype.Service;

import java.util.Map;

import static com.personal.productcatalog.utils.UserUtils.getValidClaimsCurrentUser;

@Service
public class UserService {

    public User getCurrentUser() {
        Map<String, Object> userClaims = getValidClaimsCurrentUser();

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.convertValue(userClaims, User.class);
    }
}
