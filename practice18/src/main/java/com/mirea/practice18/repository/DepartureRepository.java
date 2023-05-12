package com.mirea.practice18.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mirea.practice18.entity.Departure;

public interface DepartureRepository extends JpaRepository<Departure, Long> {
    @Query("SELECT d FROM Departure d WHERE (?1 is null or d.type = ?1) and (?2 is null or d.departureDate = ?2) and (?3 is null or d.postOffice.id = ?3)")
    List<Departure> findAll(String type, String date, Long id);
}