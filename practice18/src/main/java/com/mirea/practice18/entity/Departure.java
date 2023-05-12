package com.mirea.practice18.entity;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "departure")
@Getter
@Setter
public class Departure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private String departureDate;
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "postofficeId")
    @JsonIncludeProperties(value = { "id" })
    private PostOffice postOffice;
}