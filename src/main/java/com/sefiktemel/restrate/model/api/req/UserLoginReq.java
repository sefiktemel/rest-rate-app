package com.sefiktemel.restrate.model.api.req;

import lombok.Data;

@Data
public class UserLoginReq {
    private String email;
    private String password;
}
