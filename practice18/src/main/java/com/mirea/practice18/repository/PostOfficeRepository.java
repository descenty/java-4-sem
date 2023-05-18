package com.mirea.practice18.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mirea.practice18.model.PostOffice;

public interface PostOfficeRepository extends JpaRepository<PostOffice, Long> {
    @Query(value = "SELECT p FROM PostOffice p WHERE (?1 is null or p.name = ?1) and (?2 is null or p.cityName = ?2)")
    List<PostOffice> findAll(String name, String cityName);
}