package com.mirea.practice18.service.integration;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import com.mirea.practice18.dto.DepartureDto;
import com.mirea.practice18.model.Departure;
import com.mirea.practice18.model.PostOffice;
import com.mirea.practice18.repository.DepartureRepository;
import com.mirea.practice18.repository.PostOfficeRepository;
import com.mirea.practice18.service.DepartureService;

import jakarta.transaction.Transactional;

@SpringBootTest
@AutoConfigureTestDatabase
public class DepartureServiceTests {
    @Autowired
    private PostOfficeRepository postOfficeRepository;
    @Autowired
    private DepartureRepository departureRepository;
    @Autowired
    private DepartureService departureService;

    @BeforeEach
    void init() {
        postOfficeRepository.deleteAll();
        departureRepository.deleteAll();
        PostOffice postOffice1 = PostOffice.builder()
                .cityName("Москва")
                .name("Почтовое отделение № 1").build();
        PostOffice postOffice2 = PostOffice.builder()
                .cityName("Калуга")
                .name("Почтовое отделение № 2").build();
        postOfficeRepository.saveAll(List.of(postOffice1, postOffice2));
        departureRepository.saveAll(List.of(
                Departure.builder()
                        .type("Письмо")
                        .departureDate("2021-05-01")
                        .postOffice(postOffice1).build(),
                Departure.builder()
                        .type("Посылка")
                        .departureDate("2021-05-02")
                        .postOffice(postOffice2).build(),
                Departure.builder()
                        .type("Письмо")
                        .departureDate("2021-05-03")
                        .postOffice(postOffice2).build()));
    }

    @Test
    void getAll() {
        Assertions.assertThat(departureService.getAll(null, null, null)).hasSize(3);
        Assertions.assertThat(departureService.getAll(null, null, null).get(0).getType()).isEqualTo("Письмо");
        Assertions.assertThat(departureService.getAll(null, null, null).get(1).getType()).isEqualTo("Посылка");
        Assertions.assertThat(departureService.getAll(null, null, null).get(2).getType()).isEqualTo("Письмо");
    }

    @Test
    void filterAll() {
        Assertions.assertThat(departureService.getAll("Письмо", null, null)).hasSize(2);
        Assertions.assertThat(departureService.getAll(null, "2021-05-02", null)).hasSize(1);
        Assertions.assertThat(departureService.getAll(null, "2021-05-02", null).get(0).getType()).isEqualTo("Посылка");
        Assertions
                .assertThat(departureService.getAll(null, null, postOfficeRepository.findAll().get(1).getId()).get(0)
                        .getType())
                .isEqualTo("Посылка");
    }

    @Test
    void getById() {
        Departure departure = departureRepository.findAll().get(0);
        Assertions.assertThat(departureService.getById(departure.getId()).get().getType()).isEqualTo("Письмо");
        Assertions.assertThat(departureService.getById(departure.getId()).get().getDepartureDate())
                .isEqualTo("2021-05-01");
    }

    @Test
    @Transactional
    void add() {
        Assertions.assertThat(departureService.add(DepartureDto.builder()
                .type("Посылка")
                .departureDate("2021-05-04")
                .postOfficeId(postOfficeRepository.findAll().get(0).getId()).build()));
        Assertions.assertThat(departureService.getAll(null, null, null)).hasSize(4);
        Assertions.assertThat(departureService.getAll(null, null, null).get(3).getType()).isEqualTo("Посылка");
    }

    @Test
    @Transactional
    void remove() {
        Departure departure = departureRepository.findAll().get(0);
        Assertions.assertThat(departureService.remove(departure.getId()));
        Assertions.assertThat(departureService.getAll(null, null, null)).hasSize(2);
        Assertions.assertThat(departureService.getAll(null, null, null).get(0).getType()).isEqualTo("Посылка");
        Assertions.assertThat(departureService.getAll(null, null, null).get(1).getType()).isEqualTo("Письмо");
    }
}
