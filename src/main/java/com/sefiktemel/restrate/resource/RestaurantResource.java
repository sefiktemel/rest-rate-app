package com.sefiktemel.restrate.resource;

import com.sefiktemel.restrate.model.api.req.CommentAddReq;
import com.sefiktemel.restrate.model.api.req.CommentListReq;
import com.sefiktemel.restrate.model.api.req.UserLoginReq;
import com.sefiktemel.restrate.model.api.res.BaseRes;
import com.sefiktemel.restrate.model.api.res.CommentListRes;
import com.sefiktemel.restrate.model.api.res.RestaurantListRes;
import com.sefiktemel.restrate.model.api.res.UserLoginRes;
import com.sefiktemel.restrate.model.entity.Comment;
import com.sefiktemel.restrate.model.entity.Restaurant;
import com.sefiktemel.restrate.model.entity.User;
import io.quarkus.runtime.Startup;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.*;

@Path("/restaurant")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class RestaurantResource {
    public static final String HEADER_PARAM_SESSION_ID = "Session-Id";

    private Map<Long, Restaurant> restaurantMap = new HashMap<>();
    private List<Restaurant> restaurants = new ArrayList<>();


    @Inject
    AuthResource authResource;

    @Startup
    void startup() {
        for (int i = 0; i < 6; i++) {
            Restaurant r = new Restaurant(i, "restaurant" + i, "address" + i, 0);
            restaurantMap.put(r.getId(), r);
            restaurants.add(r);
        }
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from RestaurantResource";
    }

    @POST
    @Path("/list")
    public RestaurantListRes listComments(@HeaderParam(HEADER_PARAM_SESSION_ID) String sessionId){
        RestaurantListRes res = new RestaurantListRes();
        authResource.checkSession(res, sessionId);
        if (res.isSessionOk()) {
            res.setRestaurants(restaurants);
        }
        return res;
    }

    @POST
    @Path("/addComment")
    public BaseRes addComment(@HeaderParam(HEADER_PARAM_SESSION_ID) String sessionId, CommentAddReq req) {
        BaseRes res = new BaseRes();
        User user = authResource.checkSession(res, sessionId);
        if (res.isSessionOk()) {
            Restaurant restaurant = restaurantMap.get(req.getRestaurantId());
            if (restaurant == null) {
                res.setCode(BaseRes.CODE_NOT_FOUND);
                res.setMessage(BaseRes.MSG_NOT_FOUND);
            } else {
                Comment comment = new Comment(new Date().getTime(), user, restaurant, req.getText(), req.getRate());
                List<Comment> restaurantComments = restaurant.getComments();
                double rateTotal = restaurant.getAvgRate() * restaurantComments.size();
                rateTotal += req.getRate();
                restaurantComments.add(comment);
                double avgRate = rateTotal / restaurantComments.size();
                restaurant.setAvgRate(avgRate);
            }
        }
        return res;
    }

    @POST
    @Path("/listComments")
    public CommentListRes listComments(@HeaderParam(HEADER_PARAM_SESSION_ID) String sessionId, CommentListReq req){
        CommentListRes res = new CommentListRes();
        authResource.checkSession(res, sessionId);
        if (res.isSessionOk()) {
            Restaurant restaurant = restaurantMap.get(req.getRestaurantId());
            if (restaurant == null) {
                res.setCode(BaseRes.CODE_NOT_FOUND);
                res.setMessage(BaseRes.MSG_NOT_FOUND);
            } else {
                res.setComments(restaurant.getComments());
            }
        }
        return res;
    }
}
