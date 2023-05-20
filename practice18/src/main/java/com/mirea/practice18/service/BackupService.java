package com.mirea.practice18.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mirea.practice18.model.Departure;
import com.mirea.practice18.model.PostOffice;
import com.mirea.practice18.repository.DepartureRepository;
import com.mirea.practice18.repository.PostOfficeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@ManagedResource(objectName = "practice18:name=BackupService")
public class BackupService {
    @Value("${backup_dir}")
    private String backupDir;

    private final DepartureRepository departureRepository;
    private final PostOfficeRepository postOfficeRepository;
    private final ObjectMapper objectMapper;

    @Scheduled(fixedRate = 1800000)
    @ManagedOperation
    public void backup() throws JsonProcessingException {
        File directory = new File(backupDir);
        if (!directory.exists())
            directory.mkdir();

        Stream.of(directory.listFiles()).forEach(File::delete);
        List<Departure> departures = departureRepository.findAll();
        List<PostOffice> postOffices = postOfficeRepository.findAll();

        Stream.of(departures, postOffices).forEach(list -> {
            String fileName = list.get(0).getClass().getSimpleName().toLowerCase();
            File file = new File(backupDir + "/" + fileName + ".json");
            try (FileOutputStream fos = new FileOutputStream(file)) {
                fos.write(objectMapper.writeValueAsString(list).getBytes());
            } catch (Exception e) {
                System.out.println(e);
            }
        });
    }
}
