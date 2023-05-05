package com.mirea.practice14.PostOffice;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;

@Repository
class PostOfficeRepository {
    private List<PostOffice> postOffices = new ArrayList<>();

    @PostConstruct
    public void init() {
        postOffices.add(new PostOffice("Почта № 119571", "Москва"));
        postOffices.add(new PostOffice("Почта № 119606", "Москва"));
        postOffices.add(new PostOffice("Почта № 600005", "Владимир"));
        postOffices.add(new PostOffice("Почта № 600001", "Владимир"));
        postOffices.add(new PostOffice("Почта № 300034", "Тула"));
        postOffices.add(new PostOffice("Почта № 603003", "Нижний Новгород"));
    }

    public PostOffice[] getAll() {
        return postOffices.toArray(new PostOffice[0]);
    }

    public PostOffice[] getByName(String name) {
        return postOffices.stream().filter(postOffice -> postOffice.getName().equals(name))
                .collect(Collectors.toList()).toArray(new PostOffice[0]);
    }
    
    public PostOffice[] getByCityName(String cityName) {
        return postOffices.stream().filter(postOffice -> postOffice.getCityName().equals(cityName))
                .collect(Collectors.toList()).toArray(new PostOffice[0]);
    }

    public PostOffice[] getByNameCityName(String name, String cityName) {
        return postOffices.stream()
                .filter(postOffice -> postOffice.getName().equals(name) && postOffice.getCityName().equals(cityName))
                .collect(Collectors.toList()).toArray(new PostOffice[0]);
    }

    public PostOffice getById(int id) {
        return postOffices.stream().filter(postOffice -> postOffice.getId() == id).findFirst().orElse(null);
    }

    public void add(PostOffice postOffice) {
        postOffices.add(postOffice);
    }

    public boolean removeById(int id) {
        return postOffices.removeIf(postOffice -> postOffice.getId() == id);
            
    }

}