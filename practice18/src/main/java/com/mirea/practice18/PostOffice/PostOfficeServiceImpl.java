package com.mirea.practice18.PostOffice;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PostOfficeServiceImpl implements PostOfficeService {
    private final PostOfficeRepository postOfficeRepository;

    public List<PostOffice> getAll(String name, String cityName) {
        return postOfficeRepository.findAll(name, cityName);
    }

    public PostOffice getById(Long id) {
        return postOfficeRepository.findById(id).orElse(null);
    }

    public void add(PostOffice postOffice) {
        postOfficeRepository.save(postOffice);
    }

    public boolean removeById(Long id) {
        if (postOfficeRepository.existsById(id)) {
            postOfficeRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
