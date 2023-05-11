package com.mirea.practice18.PostOffice;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class PostOfficeServiceImpl implements PostOfficeService {
    private final PostOfficeRepository postOfficeRepository;

    public List<PostOffice> getAll(String name, String cityName) {
        log.info("Getting all PostOffices (name: {}, cityName: {})", name, cityName);
        return postOfficeRepository.findAll(name, cityName);
    }

    public PostOffice getById(Long id) {
        log.info("Getting PostOffice by id: {}", id);
        return postOfficeRepository.findById(id).orElse(null);
    }

    public void add(PostOffice postOffice) {
        log.info("Adding PostOffice: {}", postOffice);
        postOfficeRepository.save(postOffice);
    }

    public boolean removeById(Long id) {
        log.info("Removing PostOffice by id: {}", id);
        if (postOfficeRepository.existsById(id)) {
            postOfficeRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
