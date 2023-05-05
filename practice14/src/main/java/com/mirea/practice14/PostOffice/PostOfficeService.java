package com.mirea.practice14.PostOffice;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PostOfficeService {
    private final PostOfficeRepository postOfficeRepository;

    public PostOffice[] getAll() {
        return postOfficeRepository.getAll();
    }

    public PostOffice[] getByNameCityName(String name, String cityName) {
        return postOfficeRepository.getByNameCityName(name, cityName);
    }
    
    public PostOffice[] getByName(String name) {
        return postOfficeRepository.getByName(name);
    }
    
    public PostOffice[] getByCityName(String cityName) {
        return postOfficeRepository.getByCityName(cityName);
    }

    public PostOffice getById(int id) {
        return postOfficeRepository.getById(id);
    }

    public void add(PostOffice postOffice) {
        postOfficeRepository.add(postOffice);
    }

    public boolean removeById(int id) {
        return postOfficeRepository.removeById(id);
    }

}
