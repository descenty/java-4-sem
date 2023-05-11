package com.mirea.practice16.dto;

import lombok.Data;

@Data
public class DepartureDto {
    private Long id;
    private String type;
    private String departureDate;
    private Long postofficeId;
}
