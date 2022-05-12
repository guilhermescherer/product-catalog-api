package com.personal.productcatalog.controller;

import com.personal.productcatalog.dto.UserDTO;
import com.personal.productcatalog.model.User;
import com.personal.productcatalog.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.personal.productcatalog.utils.SecurityUtils.ROLE_ADMIN;
import static com.personal.productcatalog.utils.SecurityUtils.ROLE_USER;

@RestController
@RequestMapping("/user")
@Secured({ROLE_USER, ROLE_ADMIN})
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/whoami")
    @ApiOperation(value = "Return current user")
    public ResponseEntity<UserDTO> whoAmI() {
        User user = userService.getCurrentUser();
        return ResponseEntity.ok(new UserDTO(user));
    }
}
