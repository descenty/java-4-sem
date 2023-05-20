package com.mirea.practice18.service.unit;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;

import com.mirea.practice18.dto.DepartureDto;
import com.mirea.practice18.model.Departure;
import com.mirea.practice18.model.PostOffice;
import com.mirea.practice18.repository.DepartureRepository;
import com.mirea.practice18.repository.PostOfficeRepository;
import com.mirea.practice18.service.DepartureService;

@ExtendWith(MockitoExtension.class)
public class DepartureServiceTests<S extends Departure> {
    @Mock
    private DepartureRepository departureRepository;
    @Mock
    private PostOfficeRepository postOfficeRepository;
    @Captor
    ArgumentCaptor<String> typeCaptor;
    @Captor
    ArgumentCaptor<String> departureDateCaptor;
    @Captor
    ArgumentCaptor<Long> postOfficeIdCaptor;

    List<PostOffice> postOffices = List.of(
            PostOffice.builder()
                    .cityName("Москва")
                    .name("Почтовое отделение № 1")
                    .build(),
            PostOffice.builder()
                    .cityName("Санкт-Петербург")
                    .name("Почтовое отделение № 2")
                    .build());

    List<Departure> departures = List.of(
            Departure.builder()
                    .type("Письмо")
                    .departureDate("2021-05-01")
                    .postOffice(postOffices.get(0))
                    .build(),
            Departure.builder()
                    .type("Посылка")
                    .departureDate("2021-05-02")
                    .postOffice(postOffices.get(0))
                    .build(),
            Departure.builder().type("Письмо")
                    .departureDate("2021-05-03")
                    .postOffice(postOffices.get(1))
                    .build());

    @Test
    void getAll() {
        DepartureService departureService = new DepartureService(departureRepository, postOfficeRepository);
        Mockito.when(departureRepository.findAll()).thenReturn(departures);
        Assertions.assertThat(departureService.getAll(null, null, null)).isEqualTo(departures);
    }

    @Test
    void filterAll() {
        DepartureService departureService = new DepartureService(departureRepository, postOfficeRepository);

        Mockito.when(departureRepository.findAll("Письмо", "2021-05-01", 1L)).thenReturn(departures.subList(0, 1));
        Assertions.assertThat(departureService.getAll("Письмо", "2021-05-01", 1L)).isEqualTo(departures.subList(0, 1));
        Mockito.verify(departureRepository).findAll(typeCaptor.capture(), departureDateCaptor.capture(),
                postOfficeIdCaptor.capture());
        Assertions.assertThat(typeCaptor.getValue()).isEqualTo("Письмо");
        Assertions.assertThat(departureDateCaptor.getValue()).isEqualTo("2021-05-01");
        Assertions.assertThat(postOfficeIdCaptor.getValue()).isEqualTo(1);
    }

    @Test
    void getById() {
        DepartureService departureService = new DepartureService(departureRepository, postOfficeRepository);
        Mockito.when(departureRepository.findById(departures.get(0).getId()))
                .thenReturn(Optional.of(departures.get(0)));
        Assertions.assertThat(departureService.getById(departures.get(0).getId()).get()).isEqualTo(departures.get(0));
    }

    @Test
    void add() {
        DepartureDto departureDto = DepartureDto.builder()
                .type("Письмо")
                .departureDate("2021-05-06")
                .postOfficeId(postOffices.get(0).getId())
                .build();

        DepartureService departureService = new DepartureService(departureRepository, postOfficeRepository);
        Mockito.when(postOfficeRepository.findById(postOffices.get(0).getId()))
                .thenReturn(Optional.of(postOffices.get(0)));
        Departure departure = departureService.mapToEntity(departureDto).get();
        Mockito.when(departureRepository.save(Mockito.any(Departure.class))).thenReturn(departure);
        Assertions.assertThat(departureService.add(departureDto));
    }

    @Test
    void remove() {
        DepartureService departureService = new DepartureService(departureRepository, postOfficeRepository);
        Mockito.when(departureRepository.findById(departures.get(0).getId()))
                .thenReturn(Optional.of(departures.get(0)));
        Assertions.assertThat(departureService.remove(departures.get(0).getId()));
    }

}
