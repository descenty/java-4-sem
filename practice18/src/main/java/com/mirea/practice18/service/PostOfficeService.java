package com.mirea.practice18.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mirea.practice18.annotation.SendEmail;
import com.mirea.practice18.model.PostOffice;
import com.mirea.practice18.repository.PostOfficeRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostOfficeService {
    private final PostOfficeRepository postOfficeRepository;

    public List<PostOffice> getAll(String name, String cityName) {
        return postOfficeRepository.findAll(name, cityName);
    }

    public Optional<PostOffice> getById(Long id) {
        return postOfficeRepository.findById(id);
    }

    @Transactional
    @SendEmail
    public Boolean add(PostOffice postOfficeDto) {
        return postOfficeRepository.save(postOfficeDto) != null;
    }

    @Transactional
    public boolean remove(Long id) {
        return postOfficeRepository.findById(id)
                .map(postOffice -> {
                    postOfficeRepository.delete(postOffice);
                    return true;
                }).orElse(false);
    }
}
