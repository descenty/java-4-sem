package com.mirea.practice16.Departure;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mirea.practice16.PostOffice.PostOffice;
import com.mirea.practice16.PostOffice.PostOfficeRepository;
import com.mirea.practice16.dto.DepartureDto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DepartureService {
    private final DepartureRepository departureRepository;
    private final PostOfficeRepository postOfficeRepository;

    @PersistenceContext
    private final EntityManager em;

    public List<Departure> getAll(String type, String date, Long postOfficeId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Departure> cq = cb.createQuery(Departure.class);

        Root<Departure> departure = cq.from(Departure.class);
        return em.createQuery(
                cq.where(
                        type != null ? cb.like(departure.get("type"), type) : cb.conjunction(),
                        date != null ? cb.like(departure.get("departureDate"), date) : cb.conjunction(),
                        postOfficeId != null ? cb.equal(departure.get("postOffice").get("id"), postOfficeId) : cb.conjunction()))
                .getResultList();
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

    private Departure mapToEntity(DepartureDto departureDto) {
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
