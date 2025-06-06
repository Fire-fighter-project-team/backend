package com.firetrack.project.service;

import com.firetrack.project.dto.VehicleDto;
import com.firetrack.project.dto.VehicleResponseDto;
import com.firetrack.project.entity.Station;
import com.firetrack.project.entity.Vehicle;
import com.firetrack.project.repository.StationRepository;
import com.firetrack.project.repository.VehicleRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final StationRepository stationRepository;
    private final JdbcTemplate jdbcTemplate;

    public VehicleService(VehicleRepository vehicleRepository,
                          StationRepository stationRepository,
                          JdbcTemplate jdbcTemplate) {
        this.vehicleRepository = vehicleRepository;
        this.stationRepository = stationRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    public Vehicle registerVehicle(VehicleDto dto) {
        Station station = stationRepository.findByCityAndFireStation(dto.getCity(), dto.getFireStation())
                .orElseGet(() -> {
                    Station newStation = Station.builder()
                            .city(dto.getCity())
                            .fireStation(dto.getFireStation())
                            .build();
                    return stationRepository.save(newStation);
                });

        Vehicle vehicle = Vehicle.builder()
                .station(station)
                .callSign(dto.getCallSign())
                .vehicleType(dto.getVehicleType())
                .capacity(dto.getCapacity())
                .crewCount(dto.getCrewCount())
                .avlNumber(dto.getAvlNumber())
                .psLteNumber(dto.getPsLteNumber())
                .build();

        return vehicleRepository.save(vehicle);
    }

    public List<VehicleResponseDto> getAllVehicles() {
        return vehicleRepository.findAll().stream()
                .map(vehicle -> VehicleResponseDto.builder()
                        .vehicleId(vehicle.getVehicleId())
                        .callSign(vehicle.getCallSign())
                        .vehicleType(vehicle.getVehicleType())
                        .capacity(vehicle.getCapacity())
                        .crewCount(vehicle.getCrewCount())
                        .avlNumber(vehicle.getAvlNumber())
                        .psLteNumber(vehicle.getPsLteNumber())
                        .city(vehicle.getStation().getCity())
                        .fireStation(vehicle.getStation().getFireStation())
                        .build())
                .toList();
    }

    public VehicleResponseDto getVehicleById(Long id) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 차량이 존재하지 않습니다."));

        return VehicleResponseDto.builder()
                .vehicleId(vehicle.getVehicleId())
                .callSign(vehicle.getCallSign())
                .vehicleType(vehicle.getVehicleType())
                .capacity(vehicle.getCapacity())
                .crewCount(vehicle.getCrewCount())
                .avlNumber(vehicle.getAvlNumber())
                .psLteNumber(vehicle.getPsLteNumber())
                .city(vehicle.getStation().getCity())
                .fireStation(vehicle.getStation().getFireStation())
                .build();
    }

    public void deleteVehicle(Long vehicleId) {
        vehicleRepository.deleteById(vehicleId);
        resetAutoIncrement();
    }

    private void resetAutoIncrement() {
        jdbcTemplate.execute("SET @next_id = (SELECT IFNULL(MAX(vehicle_id), 0) + 1 FROM vehicles)");
        jdbcTemplate.execute("SET @sql = CONCAT('ALTER TABLE vehicles AUTO_INCREMENT = ', @next_id)");
        jdbcTemplate.execute("PREPARE stmt FROM @sql");
        jdbcTemplate.execute("EXECUTE stmt");
        jdbcTemplate.execute("DEALLOCATE PREPARE stmt");
    }

    public void importFromExcel(MultipartFile file) throws Exception {
        try (InputStream is = file.getInputStream(); Workbook workbook = WorkbookFactory.create(is)) {
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue;

                String city = row.getCell(0).getStringCellValue();
                String fireStation = row.getCell(1).getStringCellValue();
                String callSign = row.getCell(2).getStringCellValue();
                String vehicleType = row.getCell(3).getStringCellValue();
                int capacity = (int) row.getCell(4).getNumericCellValue();
                int crewCount = (int) row.getCell(5).getNumericCellValue();
                String avl = row.getCell(6).getStringCellValue();
                String ps = row.getCell(7).getStringCellValue();

                VehicleDto dto = VehicleDto.builder()
                        .city(city)
                        .fireStation(fireStation)
                        .callSign(callSign)
                        .vehicleType(vehicleType)
                        .capacity(capacity)
                        .crewCount(crewCount)
                        .avlNumber(avl)
                        .psLteNumber(ps)
                        .build();

                registerVehicle(dto);
            }
        }
    }
}