package com.mirea.practice14.Departure;

import lombok.Data;

@Data
public class Departure {
    private static int idCounter = 0;
    private int id;
    private String type;
    private String departureDate;

    public Departure(String type, String departureDate) {
        this.id = ++idCounter;
        this.type = type;
        this.departureDate = departureDate;
    }
}
