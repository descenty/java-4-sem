package com.mirea.practice14.Departure;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DepartureService {
    private final DepartureRepository departureRepository;

    public Departure[] getAll() {
        return departureRepository.getAll();
    }

    public Departure[] getByTypeDate(String type, String date) {
        return departureRepository.getByTypeDate(type, date);
    }
    
    public Departure[] getByType(String type) {
        return departureRepository.getByType(type);
    }
    
    public Departure[] getByDate(String date) {
        return departureRepository.getByDate(date);
    }

    public Departure getById(int id) {
        return departureRepository.getById(id);
    }

    public void addDeparture(Departure departure) {
        departureRepository.addDeparture(departure);
    }

    public boolean removeDepartureById(int id) {
        return departureRepository.removeDepartureById(id);
    }

}
