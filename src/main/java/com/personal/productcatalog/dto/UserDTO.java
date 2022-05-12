package com.personal.productcatalog.dto;

import com.personal.productcatalog.model.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Setter
@Getter
public class UserDTO {

    private String name;
    private String email;
    private List<String> authorities;

    public UserDTO(User user) {
        BeanUtils.copyProperties(user, this);
    }
}
