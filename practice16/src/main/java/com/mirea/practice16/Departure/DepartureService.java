package com.mirea.practice16.Departure;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DepartureService {
    private final DepartureRepository departureRepository;

    public List<Departure> getAll() {
        return departureRepository.findAll();
    }

    public void add(Departure departure) {
        departureRepository.save(departure).toString();
    }

    public boolean remove(Long id) {
        if (departureRepository.existsById(id)) {
            departureRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
