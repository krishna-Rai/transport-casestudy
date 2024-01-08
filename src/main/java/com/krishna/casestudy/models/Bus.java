package com.krishna.casestudy.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "buses")
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String busNumber;
    private int capacity;
    private String model;

    public enum BusType {
        ORDINARY,
        DELUXE
    }

    @Enumerated(EnumType.STRING)
    private BusType busType;

}
