package com.mirea.practice18.Departure;

import java.util.List;
import com.mirea.practice18.dto.DepartureDto;

public interface DepartureService {
    List<Departure> getAll(String type, String date, Long postOfficeId);

    Departure getById(Long id);

    void add(DepartureDto departureDto);

    boolean remove(Long id);

    Departure mapToEntity(DepartureDto departureDto);
}
