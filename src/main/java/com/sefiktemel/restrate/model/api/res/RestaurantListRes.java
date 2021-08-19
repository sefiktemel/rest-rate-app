package com.sefiktemel.restrate.model.api.res;

import com.sefiktemel.restrate.model.entity.Restaurant;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class RestaurantListRes extends BaseRes{
    private List<Restaurant> restaurants;
}
