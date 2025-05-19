package com.firetrack.project.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "stations")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stationId;

    private String city;
    private String fireStation;
}
