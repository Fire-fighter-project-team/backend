package com.firetrack.project.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VehicleDto {
    private String city;
    private String fireStation;
    private String callSign;
    private String vehicleType;
    private int capacity;
    private int crewCount;
    private String avlNumber;
    private String psLteNumber;
}
