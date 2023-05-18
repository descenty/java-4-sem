package com.mirea.practice18.service;

import java.io.File;
import java.io.FileOutputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    // @Transactional
    public void backup() throws JsonProcessingException {
        File directory = new File(backupDir);
        if (!directory.exists()) {
            directory.mkdir();
        }
        for (File file : directory.listFiles()) {
            file.delete();
        }

        File file1 = new File(backupDir + "/departure.txt");
        try (FileOutputStream fos = new FileOutputStream(file1)) {
            fos.write(objectMapper.writeValueAsString(departureRepository.findAll()).getBytes());
        } catch (Exception e) {
            System.out.println(e);
        }
        File file2 = new File(backupDir + "/post_office.txt");
        try (FileOutputStream fos = new FileOutputStream(file2)) {
            fos.write(objectMapper.writeValueAsString(postOfficeRepository.findAll()).getBytes());
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
