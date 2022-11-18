package com.example.tire_warehouse.dto.uses;

import com.example.tire_warehouse.entity.enumEntity.Season;

public class SeasonMapper {

    public static String season(Season season) {
        if (season == null) {
            return null;
        }
        return season.toString();
    }

    public static Season season(String season) {
        if (season == null) {
            return null;
        }
            return Season.valueOf(season);
    }
}
