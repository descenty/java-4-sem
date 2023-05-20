package com.mirea.practice18.service.unit;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mirea.practice18.model.PostOffice;
import com.mirea.practice18.repository.PostOfficeRepository;
import com.mirea.practice18.service.PostOfficeService;

@ExtendWith(MockitoExtension.class)
public class PostOfficeServiceTests {
    @Mock
    private PostOfficeRepository postOfficeRepository;

    List<PostOffice> postOffices = List.of(
            PostOffice.builder()
                    .cityName("Москва")
                    .name("Почтовое отделение № 1")
                    .build(),
            PostOffice.builder()
                    .cityName("Санкт-Петербург")
                    .name("Почтовое отделение № 2")
                    .build());

    @Test
    void getAll() {
        PostOfficeService postOfficeService = new PostOfficeService(postOfficeRepository);
        Mockito.when(postOfficeRepository.findAll((String) null, (String) null)).thenReturn(postOffices);
        Assertions.assertThat(postOfficeService.getAll(null, null)).isEqualTo(postOffices);
    }

    @Test
    void getById() {
        PostOfficeService postOfficeService = new PostOfficeService(postOfficeRepository);
        Mockito.when(postOfficeRepository.findById(1L)).thenReturn(Optional.of(postOffices.get(0)));
        Assertions.assertThat(postOfficeService.getById(1L).get()).isEqualTo(postOffices.get(0));
    }

    @Test
    void filterAll() {
        PostOfficeService postOfficeService = new PostOfficeService(postOfficeRepository);
        postOfficeService.getAll("Москва", "Почтовое отделение № 1");
        Mockito.verify(postOfficeRepository).findAll("Москва", "Почтовое отделение № 1");
    }

    @Test
    void save() {
        PostOfficeService postOfficeService = new PostOfficeService(postOfficeRepository);
        Mockito.when(postOfficeRepository.save(postOffices.get(0))).thenReturn(postOffices.get(0));
        Assertions.assertThat(postOfficeService.add(postOffices.get(0)));
    }

    @Test
    void remove() {
        PostOfficeService postOfficeService = new PostOfficeService(postOfficeRepository);
        Mockito.when(postOfficeRepository.findById(1L)).thenReturn(Optional.of(postOffices.get(0)));
        Assertions.assertThat(postOfficeService.remove(1L));
    }

}
