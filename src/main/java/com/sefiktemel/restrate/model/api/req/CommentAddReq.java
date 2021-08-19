package com.sefiktemel.restrate.model.api.req;

import lombok.Data;

@Data
public class CommentAddReq {
    private long restaurantId;
    private String text;
    private int rate;
}
