# 🎫 2024-05-19 기준 기능 설명서

## 📦 기술 스택

- Java 17
- Spring Boot 3.x
- Spring Web / JPA / JDBC
- MySQL
- Apache POI (엑셀 파일 처리)
- HTML (Vanilla JS 기반 클라이언트)
- Gradle

---

## 🔧 주요 기능 설명

| 기능 | 설명 |
|------|------|
| 🚗 차량 등록 | 폼 입력 or 엑셀 업로드로 차량 등록 |
| 📋 전체 목록 조회 | 등록된 차량들을 테이블로 조회 |
| 🔍 ID로 조회 | `/api/vehicles/{id}` 로 개별 차량 확인 |
| 🗑️ 삭제 | 버튼 클릭 시 차량 삭제 + AUTO_INCREMENT 재설정 |
| 📁 Excel 업로드 | `.xlsx` 파일로 다건 등록 가능 (Apache POI 사용) |

---

## 🗂️ API 명세서

| 메서드 | 경로 | 설명 |
|--------|------|------|
| `POST` | `/api/vehicles/register` | 차량 등록 |
| `POST` | `/api/vehicles/upload` | 엑셀 업로드 등록 |
| `GET` | `/api/vehicles/all` | 전체 차량 목록 |
| `GET` | `/api/vehicles/{id}` | 단건 차량 조회 |
| `DELETE` | `/api/vehicles/{id}` | 차량 삭제 + ID 리셋 |

---

## 🧾 엑셀 업로드 형식

`.xlsx` 파일의 첫 줄은 반드시 헤더, 이후는 데이터입니다.

| 시도 | 소방서 | 호출명 | 차량종류 | 용량 | 인원 | AVL | PS-LTE |
|------|--------|--------|----------|------|------|------|--------|
| 대구 | 강북   | 강북중펌 | 중펌 | 3000 | 3 | 010-1111-1111 | 013-2222-2222 |

- 컬럼 순서 고정
- 모든 필드는 필수
- 최대 1개의 시트만 처리

---

📁 프로젝트 구조
```arduino
com.firetrack.project
├── controller
│   └── VehicleController.java
├── service
│   └── VehicleService.java
├── dto
│   ├── VehicleDto.java
│   └── VehicleResponseDto.java
├── entity
│   ├── Vehicle.java
│   └── Station.java
├── repository
│   ├── VehicleRepository.java
│   └── StationRepository.java
└── resources
    └── static/index.html
```
