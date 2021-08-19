package com.sefiktemel.restrate.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Comment {
    private long id;
    private User user;
    private Restaurant restaurant;
    private String text;
    private int rate;
}
