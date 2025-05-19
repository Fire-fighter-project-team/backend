package com.firetrack.project.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonPropertyOrder({
        "vehicleId",
        "city",
        "fireStation",
        "callSign",
        "vehicleType",
        "capacity",
        "crewCount",
        "avlNumber",
        "psLteNumber"
})
public class VehicleResponseDto {
    private Long vehicleId;
    private String city;
    private String fireStation;
    private String callSign;
    private String vehicleType;
    private int capacity;
    private int crewCount;
    private String avlNumber;
    private String psLteNumber;
}
