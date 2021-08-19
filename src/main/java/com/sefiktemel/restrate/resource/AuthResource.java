package com.sefiktemel.restrate.resource;


import com.sefiktemel.restrate.model.api.res.BaseRes;
import com.sefiktemel.restrate.model.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@ApplicationScoped
public class AuthResource {
    private final static Logger applicationLogger = LoggerFactory.getLogger(AuthResource.class);

    private Map<String, User> sessionMap = new HashMap<>();//session-id length 36

    public String createSession(User user) {
        String sessionId = UUID.randomUUID().toString();
        sessionMap.put(sessionId, user);
        applicationLogger.trace("session {} created for user {}", sessionId, user.getId());
        return sessionId;
    }

    public User checkSession(BaseRes res, String sessionId) {
        User user = sessionMap.get(sessionId);
        if (user == null) {
            res.setCode(BaseRes.CODE_SESSION_NOT_FOUND);
            res.setMessage(BaseRes.MSG_SESSION_NOT_FOUND);
        }
        return user;
    }
}
