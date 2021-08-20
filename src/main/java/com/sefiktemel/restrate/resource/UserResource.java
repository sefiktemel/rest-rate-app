package com.sefiktemel.restrate.resource;

import com.sefiktemel.restrate.model.api.req.UserLoginReq;
import com.sefiktemel.restrate.model.api.res.BaseRes;
import com.sefiktemel.restrate.model.api.res.UserLoginRes;
import com.sefiktemel.restrate.model.entity.User;
import io.quarkus.runtime.Startup;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.*;

@Path("/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class UserResource {

    @Inject
    AuthResource authResource;

    private List<User> users = new ArrayList<>();

    @Startup
    public void onStart(){
        for (int i = 0; i < 10; i++) {
            User u = new User(i,"user"+i,"user"+i+"@mail.com","password"+i);
            users.add(u);
        }
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from UserResource";
    }

    @POST
    @Path("/login")
    public UserLoginRes login(UserLoginReq req){
        UserLoginRes res = new UserLoginRes();
        Optional<User> optUser = users.stream().filter(u -> u.checkPassword(req)).findAny();
        if(optUser.isPresent()){
            String sessionId = authResource.createSession(optUser.get());
            res.setSessionId(sessionId);
        }else{
            res.setCode(BaseRes.CODE_NOT_FOUND);
            res.setMessage(BaseRes.MSG_NOT_FOUND);
        }
        return res;
    }
}
