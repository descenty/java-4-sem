package com.mirea.practice14.PostOffice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/postoffices")
@AllArgsConstructor
public class PostOfficeController {
    private final PostOfficeService postOfficeService;

    @GetMapping("/")
    public PostOffice[] getAll(@RequestParam(required = false) String name,
            @RequestParam(required = false) String cityName) {
        if (name != null && cityName != null) {
            return postOfficeService.getByNameCityName(name, cityName);
        } else if (name != null) {
            return postOfficeService.getByName(name);
        } else if (cityName != null) {
            return postOfficeService.getByCityName(cityName);
        } else {
            return postOfficeService.getAll();
        }
    }

    @GetMapping("/{id}/")
    public PostOffice getById(@PathVariable int id) {
        return postOfficeService.getById(id);
    }

    @PostMapping("/")
    public void add(@RequestBody PostOffice postOffice) {
        postOfficeService.add(postOffice);
    }

    @DeleteMapping("/{id}/")
    public ResponseEntity<?> remove(@PathVariable int id) {
        return postOfficeService.removeById(id) ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }

}
