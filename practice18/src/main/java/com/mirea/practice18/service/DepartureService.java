package com.mirea.practice18.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mirea.practice18.dto.DepartureDto;
import com.mirea.practice18.model.Departure;
import com.mirea.practice18.model.PostOffice;
import com.mirea.practice18.repository.DepartureRepository;
import com.mirea.practice18.repository.PostOfficeRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DepartureService {
    private final DepartureRepository departureRepository;
    private final PostOfficeRepository postOfficeRepository;
    private final EmailService emailService;
    @Value("${send_email}")
    private boolean sendEmail;

    public List<Departure> getAll(String type, String date, Long postOfficeId) {
        return departureRepository.findAll(type, date, postOfficeId);
    }

    public Departure getById(Long id) {
        return departureRepository.findById(id).orElse(null);
    }

    @Transactional
    public void add(DepartureDto departureDto) {
        Departure departure = departureRepository.save(mapToEntity(departureDto));
        if (sendEmail) {
            String subject = "Создано отправление № " + departure.getId();
            String msgBody = String.format(
                    "Отправление № %s\nТип: %s,\nДата отправления: %s\nПочтовое отделение: %s, г. %s",
                    departure.getId(),
                    departure.getType(),
                    departure.getDepartureDate(),
                    departure.getPostOffice().getName(),
                    departure.getPostOffice().getCityName());
            emailService.sendEmail(subject, msgBody);
        }
    }

    @Transactional
    public boolean remove(Long id) {
        if (departureRepository.existsById(id)) {
            departureRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Departure mapToEntity(DepartureDto departureDto) {
        Departure departure = new Departure();
        departure.setId(departureDto.getId());
        departure.setType(departureDto.getType());
        departure.setDepartureDate(departureDto.getDepartureDate());
        Optional<PostOffice> postOffice = postOfficeRepository.findById(departureDto.getPostofficeId());
        if (postOffice.isPresent()) {
            departure.setPostOffice(postOffice.get());
        }
        return departure;
    }
}
