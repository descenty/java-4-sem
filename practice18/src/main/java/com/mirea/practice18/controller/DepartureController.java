package com.mirea.practice18.controller;

import java.util.List;

import org.springframework.context.annotation.Role;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mirea.practice18.dto.DepartureDto;
import com.mirea.practice18.model.Departure;
import com.mirea.practice18.model.ERole;
import com.mirea.practice18.service.DepartureService;

import jakarta.annotation.security.RolesAllowed;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/departures")
@AllArgsConstructor
public class DepartureController {
    private final DepartureService departureService;

    @GetMapping("/")
    public List<Departure> getAll(@RequestParam(required = false) String type,
            @RequestParam(required = false) String date, @RequestParam(required = false) Long postOfficeId) {
        return departureService.getAll(type, date, postOfficeId);
    }

    @GetMapping("/{id}/")
    public ResponseEntity<Departure> getById(@PathVariable Long id) {
        Departure departure = departureService.getById(id);
        return departure != null ? ResponseEntity.ok(departure) : ResponseEntity.notFound().build();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/")
    public void add(@RequestBody DepartureDto departureDto) {
        departureService.add(departureDto);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}/")
    public ResponseEntity<?> remove(@PathVariable Long id) {
        return departureService.remove(id) ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }

}
