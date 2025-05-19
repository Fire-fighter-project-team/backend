package com.firetrack.project.controller;

import com.firetrack.project.dto.VehicleDto;
import com.firetrack.project.dto.VehicleResponseDto;
import com.firetrack.project.entity.Vehicle;
import com.firetrack.project.service.VehicleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
@CrossOrigin(origins = "*")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping("/register")
    public ResponseEntity<Vehicle> registerVehicle(@RequestBody VehicleDto dto) {
        Vehicle savedVehicle = vehicleService.registerVehicle(dto);
        return ResponseEntity.ok(savedVehicle);
    }

    @GetMapping("/all")
    public ResponseEntity<List<VehicleResponseDto>> getAllVehicles() {
        return ResponseEntity.ok(vehicleService.getAllVehicles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleResponseDto> getVehicleById(@PathVariable Long id) {
        return ResponseEntity.ok(vehicleService.getVehicleById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVehicle(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
        return ResponseEntity.ok("✅ 차량 삭제 및 AUTO_INCREMENT 재설정 완료");
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadExcel(@RequestParam("file") MultipartFile file) {
        try {
            vehicleService.importFromExcel(file);
            return ResponseEntity.ok("✅ 엑셀 업로드 성공");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("❌ 업로드 실패: " + e.getMessage());
        }
    }
}