package com.mirea.practice14.PostOffice;

import lombok.Data;

@Data
public class PostOffice {
    private static int idCounter = 0;
    private int id;
    private String name;
    private String cityName;

    public PostOffice(String name, String cityName) {
        this.id = ++idCounter;
        this.name = name;
        this.cityName = cityName;
    }
}
