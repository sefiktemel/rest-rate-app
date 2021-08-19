package com.sefiktemel.restrate.model.api.res;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserLoginRes extends BaseRes{
    private String sessionId;
}
