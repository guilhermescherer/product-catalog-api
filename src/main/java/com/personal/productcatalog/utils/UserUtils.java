package com.personal.productcatalog.utils;

import com.personal.productcatalog.model.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class UserUtils {

    private static final List<String> USER_FIELDS = Arrays.stream(User.class.getDeclaredFields()).map(Field::getName).toList();

    public static Map<String, Object> getValidClaimsCurrentUser() {
        Map<String, Object> allClaims = getAllClaimsCurrentUser();

        return allClaims.entrySet().stream()
                .filter(UserUtils::isValidUserClaim)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static Map<String, Object> getAllClaimsCurrentUser() {
        return  ((Jwt) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal())
                .getClaims();
    }

    public static boolean isValidUserClaim(Map.Entry<String, Object> entry) {
        return USER_FIELDS.stream().anyMatch(field -> field.equals(entry.getKey()));
    }
}
