package com.mirea.practice14.Departure;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;

@Repository
class DepartureRepository {
    private List<Departure> departures = new ArrayList<>();

    @PostConstruct
    public void init() {
        departures.add(new Departure("Письмо", "01.05.2023"));
        departures.add(new Departure("Посылка", "02.05.2023"));
        departures.add(new Departure("Письмо", "03.05.2023"));
        departures.add(new Departure("Посылка", "04.05.2023"));
        departures.add(new Departure("Посылка", "05.05.2023"));
    }

    public Departure[] getAll() {
        return departures.toArray(new Departure[0]);
    }

    public Departure[] getByType(String type) {
        return departures.stream().filter(departure -> departure.getType().equals(type))
                .collect(Collectors.toList()).toArray(new Departure[0]);
    }
    
    public Departure[] getByDate(String date) {
        return departures.stream().filter(departure -> departure.getDepartureDate().equals(date))
                .collect(Collectors.toList()).toArray(new Departure[0]);
    }

    public Departure[] getByTypeDate(String type, String date) {
        return departures.stream()
                .filter(departure -> departure.getType().equals(type) && departure.getDepartureDate().equals(date))
                .collect(Collectors.toList()).toArray(new Departure[0]);
    }

    public Departure getById(int id) {
        return departures.stream().filter(departure -> departure.getId() == id).findFirst().orElse(null);
    }

    public void addDeparture(Departure departure) {
        departures.add(departure);
    }

    public boolean removeDepartureById(int id) {
        return departures.removeIf(departure -> departure.getId() == id);
            
    }

}