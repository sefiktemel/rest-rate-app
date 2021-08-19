package com.sefiktemel.restrate.model.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Restaurant {
    private long id;
    private String name;
    private String address;
    private double avgRate;
    private List<Comment> comments = new ArrayList<>();

    public Restaurant(long id, String name, String address, double avgRate) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.avgRate = avgRate;
    }
}
