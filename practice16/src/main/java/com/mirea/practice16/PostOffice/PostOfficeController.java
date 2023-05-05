package com.mirea.practice16.PostOffice;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/postoffices")
@AllArgsConstructor
public class PostOfficeController {
    private final PostOfficeService postOfficeService;

    @GetMapping("/")
    public List<PostOffice> getAll() {
        return postOfficeService.getAll();
    }

    @PostMapping("/")
    public void add(@RequestBody PostOffice postOffice) {
        postOfficeService.add(postOffice);
    }

    @DeleteMapping("/{id}/")
    public ResponseEntity<?> remove(@PathVariable Long id) {
        return postOfficeService.removeById(id) ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }

}
