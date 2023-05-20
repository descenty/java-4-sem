package com.mirea.practice18.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DepartureDto {
    private Long id;
    private String type;
    private String departureDate;
    private Long postOfficeId;
}
