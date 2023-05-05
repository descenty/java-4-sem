package com.mirea.practice15.Departure;

import java.util.List;

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
    public List<Departure> getAll() {
        return departureService.getAll();
    }

    @PostMapping("/")
    public void add(@RequestBody Departure departure) {
        departureService.add(departure);
    }

    @DeleteMapping("/{id}/")
    public ResponseEntity<?> remove(@PathVariable Long id) {
        return departureService.remove(id) ? ResponseEntity.ok().build()
        : ResponseEntity.notFound().build();
    }

}
