package com.mirea.practice16.PostOffice;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PostOfficeService {
    private final PostOfficeRepository postOfficeRepository;
    private final EntityManager em;

    public List<PostOffice> getAll(String name, String cityName) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<PostOffice> cq = cb.createQuery(PostOffice.class);

        Root<PostOffice> departure = cq.from(PostOffice.class);
        return em.createQuery(
                cq.where(
                        name != null ? cb.like(departure.get("name"), name) : cb.conjunction(),
                        cityName != null ? cb.like(departure.get("cityName"), cityName) : cb.conjunction()))
                .getResultList();
    }

    public PostOffice getById(Long id) {
        return postOfficeRepository.findById(id).orElse(null);
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
