package com.personal.productcatalog.service;

import com.personal.productcatalog.model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    private static final String DEFAULT_NAME = "Guilherme Scherer";
    private static final String DEFAULT_EMAIL = "email@123.com.br";

    @InjectMocks
    private UserService userService;

    @BeforeAll
    static void setUp() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("name", DEFAULT_NAME);
        claims.put("email", DEFAULT_EMAIL);

        Authentication authentication = Mockito.mock(Authentication.class);
        Jwt jwt = Mockito.mock(Jwt.class);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        SecurityContextHolder.setContext(securityContext);

        given(securityContext.getAuthentication()).willReturn(authentication);
        given(authentication.getPrincipal()).willReturn(jwt);
        given(jwt.getClaims()).willReturn(claims);
    }

    @Test
    public void shouldReturnCurrentUser(){
        User user = userService.getCurrentUser();

        assertEquals(DEFAULT_NAME, user.getName());
        assertEquals(DEFAULT_EMAIL, user.getEmail());
    }
}
