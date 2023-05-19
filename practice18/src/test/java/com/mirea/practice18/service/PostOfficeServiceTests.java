package com.mirea.practice18.service;

import java.util.List;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

import com.mirea.practice18.model.PostOffice;
import com.mirea.practice18.repository.PostOfficeRepository;

import lombok.RequiredArgsConstructor;

@DataJpaTest
// @SpringBootTest
public class PostOfficeServiceTests {
    @Autowired
    private PostOfficeRepository postOfficeRepository;
    @Autowired
    private PostOfficeService postOfficeService;

    @BeforeEach
    public void init() {
        PostOffice postOffice1 = new PostOffice();
        postOffice1.setCityName("Москва");
        postOffice1.setName("Почтовое отделение № 1");
        PostOffice postOffice2 = new PostOffice();
        postOffice2.setCityName("Калуга");
        postOffice2.setName("Почтовое отделение № 2");
        postOfficeRepository.save(postOffice1);
        postOfficeRepository.save(postOffice2);
    }

    
    @Test
    void getAll() {
        System.out.println(postOfficeService.getAll(null, null));
        // Mockito.when(postOfficeRepository.findAll()).thenReturn(postOffices());
        // PostOfficeService postOfficeService = new PostOfficeService(postOfficeRepository, emailService);
        // Assertions.assertEquals(2, postOfficeService.getAll(null, null).size());
        // Assertions.assertEquals("Москва", postOfficeService.getAll("№ 1", "Москва").get(0).getCityName());
        // Assertions.assertEquals("Почтовое отделение № 1", postOfficeService.getAll("№ 1", null).get(0).getName());
        // Assertions.assertEquals("Калуга", postOfficeService.getAll(null, null).get(1).getCityName());
        // Assertions.assertEquals("Почтовое отделение № 2", postOfficeService.getAll(null, "Калуга").get(1).getName());

    }

    @Test
    void getById() {
    }
}
