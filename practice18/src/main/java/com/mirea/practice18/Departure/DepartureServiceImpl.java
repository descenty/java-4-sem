package com.mirea.practice18.Departure;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mirea.practice18.PostOffice.PostOffice;
import com.mirea.practice18.PostOffice.PostOfficeRepository;
import com.mirea.practice18.dto.DepartureDto;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DepartureServiceImpl implements DepartureService {
    private final DepartureRepository departureRepository;
    private final PostOfficeRepository postOfficeRepository;

    public List<Departure> getAll(String type, String date, Long postOfficeId) {
        return departureRepository.findAll(type, date, postOfficeId);
    }

    public Departure getById(Long id) {
        return departureRepository.findById(id).orElse(null);
    }

    public void add(DepartureDto departureDto) {
        departureRepository.save(mapToEntity(departureDto));
    }

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
