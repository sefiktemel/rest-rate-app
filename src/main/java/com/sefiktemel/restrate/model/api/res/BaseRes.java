package com.sefiktemel.restrate.model.api.res;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class BaseRes {
    public static final int CODE_OK = 200;
    public static final int CODE_NOT_FOUND = 400;
    public static final int CODE_SESSION_NOT_FOUND = 401;
    public static final int CODE_NOT_ALLOWED = 402;
    public static final String MSG_NOT_FOUND = "not found";
    public static final String MSG_SESSION_NOT_FOUND = "session not found";

    private int code = CODE_OK;
    private String message = "Success";

    @JsonIgnore
    public boolean isSessionOk() {
        return code == CODE_OK;
    }
}
