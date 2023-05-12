package com.mirea.practice18.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mirea.practice18.entity.PostOffice;
import com.mirea.practice18.service.PostOfficeService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/postoffices")
@AllArgsConstructor
public class PostOfficeController {
    private final PostOfficeService postOfficeService;

    @GetMapping("/")
    public List<PostOffice> getAll(@RequestParam(required = false) String name,
            @RequestParam(required = false) String cityName) {
        return postOfficeService.getAll(name, cityName);
    }

    @GetMapping("/{id}/")
    public ResponseEntity<PostOffice> getById(@PathVariable Long id) {
        PostOffice postOffice = postOfficeService.getById(id);
        return postOffice != null ? ResponseEntity.ok(postOffice) : ResponseEntity.notFound().build();
    }

    @PostMapping("/")
    public void add(@RequestBody PostOffice postOffice) {
        postOfficeService.add(postOffice);
    }

    @DeleteMapping("/{id}/")
    public ResponseEntity<?> remove(@PathVariable Long id) {
        return postOfficeService.remove(id) ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }

}
