package com.firetrack.project.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "vehicles")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vehicleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "station_id")
    private Station station;

    private String callSign;
    private String vehicleType;
    private int capacity;
    private int crewCount;
    private String avlNumber;
    private String psLteNumber;

    @Column(updatable = false, insertable = false)
    private LocalDateTime registeredAt;
}
