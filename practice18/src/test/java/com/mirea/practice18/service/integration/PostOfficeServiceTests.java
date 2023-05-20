package com.mirea.practice18.service.integration;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import com.mirea.practice18.model.PostOffice;
import com.mirea.practice18.repository.PostOfficeRepository;
import com.mirea.practice18.service.PostOfficeService;

import jakarta.transaction.Transactional;

@SpringBootTest
@AutoConfigureTestDatabase
public class PostOfficeServiceTests {
    @Autowired
    private PostOfficeRepository postOfficeRepository;
    @Autowired
    private PostOfficeService postOfficeService;

    @BeforeEach
    void init() {
        postOfficeRepository.deleteAll();
        postOfficeRepository.saveAll(List.of(
                PostOffice.builder()
                        .cityName("Москва")
                        .name("Почтовое отделение № 1").build(),
                PostOffice.builder()
                        .cityName("Калуга")
                        .name("Почтовое отделение № 2").build(),
                PostOffice.builder()
                        .cityName("Москва")
                        .name("Почтовое отделение № 3").build()));
    }

    @Test
    void getAll() {
        Assertions.assertThat(postOfficeService.getAll(null, null)).hasSize(3);
        Assertions.assertThat(postOfficeService.getAll(null, null).get(0).getCityName()).isEqualTo("Москва");
        Assertions.assertThat(postOfficeService.getAll(null, null).get(1).getCityName()).isEqualTo("Калуга");
        Assertions.assertThat(postOfficeService.getAll(null, null).get(2).getCityName()).isEqualTo("Москва");
    }

    @Test
    void filterAll() {
        Assertions.assertThat(postOfficeService.getAll("№ 1", "Москва").get(0).getCityName()).isEqualTo("Москва");
        Assertions.assertThat(postOfficeService.getAll("№ 1", "Москва").get(0).getName())
                .isEqualTo("Почтовое отделение № 1");
        Assertions.assertThat(postOfficeService.getAll("№ 2", null).get(0).getCityName()).isEqualTo("Калуга");
        Assertions.assertThat(postOfficeService.getAll(null, "Калуга").get(0).getName())
                .isEqualTo("Почтовое отделение № 2");
        Assertions.assertThat(postOfficeService.getAll(null, "Москва")).hasSize(2);
    }

    @Test
    void getById() {
        PostOffice postOffice = postOfficeRepository.findAll().get(0);
        Assertions.assertThat(postOfficeService.getById(postOffice.getId()).get().getCityName()).isEqualTo("Москва");
        Assertions.assertThat(postOfficeService.getById(postOffice.getId()).get().getName())
                .isEqualTo("Почтовое отделение № 1");
    }

    @Test
    @Transactional
    void add() {
        PostOffice postOffice = PostOffice.builder()
                .cityName("Москва")
                .name("Почтовое отделение № 4").build();
        Assertions.assertThat(postOfficeService.add(postOffice));
        Assertions.assertThat(postOfficeService.getAll(null, null)).hasSize(4);
        Assertions.assertThat(postOfficeService.getAll(null, null).get(3).getCityName()).isEqualTo("Москва");
        Assertions.assertThat(postOfficeService.getAll(null, null).get(3).getName())
                .isEqualTo("Почтовое отделение № 4");
    }

    @Test
    @Transactional
    void remove() {
        postOfficeService.remove(postOfficeRepository.findAll().get(0).getId());
        Assertions.assertThat(postOfficeService.getAll(null, null)).hasSize(2);
        Assertions.assertThat(postOfficeService.getAll(null, null).get(0).getName())
                .isEqualTo("Почтовое отделение № 2");
        Assertions.assertThat(postOfficeService.getAll(null, null).get(1).getName())
                .isEqualTo("Почтовое отделение № 3");
    }
}
