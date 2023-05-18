package com.mirea.practice18.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mirea.practice18.model.PostOffice;
import com.mirea.practice18.repository.PostOfficeRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PostOfficeService {
    private final PostOfficeRepository postOfficeRepository;
    private final EmailService emailService;

    public List<PostOffice> getAll(String name, String cityName) {
        return postOfficeRepository.findAll(name, cityName);
    }

    public PostOffice getById(Long id) {
        return postOfficeRepository.findById(id).orElse(null);
    }

    @Transactional
    public void add(PostOffice postOfficeDto) {
        PostOffice postOffice = postOfficeRepository.save(postOfficeDto);
        String subject = "Новое почтовое отделение в г. " + postOffice.getCityName();
        String msgBody = String.format(
                "Почтовое отделение № %s\n%s, г. %s",
                postOffice.getId(),
                postOffice.getName(),
                postOffice.getCityName());
        emailService.sendEmail(subject, msgBody);
    }

    @Transactional
    public boolean remove(Long id) {
        if (postOfficeRepository.existsById(id)) {
            postOfficeRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
