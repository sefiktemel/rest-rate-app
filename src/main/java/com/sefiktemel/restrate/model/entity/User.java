package com.sefiktemel.restrate.model.entity;

import com.sefiktemel.restrate.model.api.req.UserLoginReq;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private long id;
    private String username;
    private String email;
    private String password;

    public boolean checkPassword(UserLoginReq req) {
        return req.getEmail().equalsIgnoreCase(email) && req.getPassword().equals(password);
    }
}
