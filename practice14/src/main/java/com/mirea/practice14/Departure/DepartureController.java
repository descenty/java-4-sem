package com.mirea.practice14.Departure;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/departures")
@AllArgsConstructor
public class DepartureController {
    private final DepartureService departureService;

    @GetMapping("/")
    public Departure[] getAllDepartures(@RequestParam(required = false) String type,
            @RequestParam(required = false) String date) {
        if (type != null && date != null) {
            return departureService.getByTypeDate(type, date);
        } else if (type != null) {
            return departureService.getByType(type);
        } else if (date != null) {
            return departureService.getByDate(date);
        } else {
            return departureService.getAll();
        }
    }

    @GetMapping("/{id}/")
    public Departure getDepartureById(@PathVariable int id) {
        return departureService.getById(id);
    }

    @PostMapping("/")
    public void addDeparture(@RequestBody Departure departure) {
        departureService.addDeparture(departure);
    }

    @DeleteMapping("/{id}/")
    public ResponseEntity<?> removeDepartureById(@PathVariable int id) {
        return departureService.removeDepartureById(id) ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }

}
