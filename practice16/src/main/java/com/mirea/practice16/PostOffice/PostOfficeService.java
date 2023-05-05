package com.mirea.practice16.PostOffice;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PostOfficeService {
    private final PostOfficeRepository postOfficeRepository;

    public List<PostOffice> getAll() {
        return postOfficeRepository.findAll();
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
