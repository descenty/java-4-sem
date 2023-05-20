package com.mirea.practice18.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mirea.practice18.annotation.SendEmail;
import com.mirea.practice18.dto.DepartureDto;
import com.mirea.practice18.model.Departure;
import com.mirea.practice18.repository.DepartureRepository;
import com.mirea.practice18.repository.PostOfficeRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DepartureService {
    private final DepartureRepository departureRepository;
    private final PostOfficeRepository postOfficeRepository;

    public List<Departure> getAll(String type, String date, Long postOfficeId) {
        return departureRepository.findAll(type, date, postOfficeId);
    }

    public Optional<Departure> getById(Long id) {
        return departureRepository.findById(id);
    }

    @Transactional
    @SendEmail
    public Boolean add(DepartureDto departureDto) {
        return mapToEntity(departureDto)
                .map(departureRepository::save)
                .map(departure -> true)
                .orElse(false);
    }

    @Transactional
    public boolean remove(Long id) {
        return departureRepository.findById(id)
                .map(departure -> {
                    departureRepository.delete(departure);
                    return true;
                }).orElse(false);
    }

    public Optional<Departure> mapToEntity(DepartureDto departureDto) {
        return postOfficeRepository.findById(departureDto.getPostOfficeId())
                .map(postOffice -> Departure.builder()
                        .type(departureDto.getType())
                        .departureDate(departureDto.getDepartureDate())
                        .postOffice(postOffice)
                        .build())
                .map(Optional::of).orElse(Optional.empty());
    }
}
