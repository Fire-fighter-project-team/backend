# 구현 현황 vs 요청사항 정리 (2025-05-20 류지우 레포 기준)

## 1. 차량 등록 및 상태 이동 (출동/복귀)
| 요청사항               | 구현 상태                |
| ------------------ | -------------------- |
| 차량 등록 기능           | ✅ 구현됨 (단일 폼, 엑셀 업로드) |
| 출동 버튼 → 활동 차량으로 이동 | ❌ 미구현                |
| 복귀 버튼 → 대기 차량으로 이동 | ❌ 미구현                |

* 📌 TODO: 차량 상태 컬럼(standby, active, returned) 추가 후 상태 전환 API 및 UI 버튼 구성 필요

## 2. 입력 항목 및 데이터 구조
| 항목                                      | 구현 상태            |
| --------------------------------------- | ---------------- |
| 연번(vehicleId), 시도, 소방서, 호출명, 차종, 용량, 인원 | ✅ 구현 완료          |
| AVL 단말기 번호, PS-LTE 번호                   | ✅ 구현 완료          |
| 등록 시 자동 반영 (총괄집계용)                      | ❌ 통계 DB/쿼리 아직 없음 |

* 📌 TODO: summary_view 또는 통계 계산용 API 설계 필요

## 3. 총괄 통계 집계
| 항목                   | 구현 상태 |
| -------------------- | ----- |
| 지역별 차량 수, 대기/출동/복귀 수 | ❌ 미구현 |
| 차종별 통계 (중형펌프, 헬기 등)  | ❌ 미구현 |

* 📌 TODO: 집계용 View or API 생성 + 실시간 갱신 필요

## 4. 출동 기록 및 이력 관리
| 항목              | 구현 상태 |
| --------------- | ----- |
| 출동 이력 저장        | ❌ 미구현 |
| 복귀 후 상태 → 대기 전환 | ❌ 미구현 |

* 📌 TODO: dispatch_logs 테이블 추가 후, 출동/복귀 시 로그 기록 및 시간 저장

## 5. 현장 활용성 / 실시간 동기화
| 항목         | 구현 상태                |
| ---------- | -------------------- |
| 온라인 환경 구축  | ✅ 웹 기반 구현 완료         |
| 오프라인 대응    | ❌ 미구현                |
| 2대 노트북 동기화 | ❌ 실시간 WebSocket 등 없음 |

* 📌 TODO: WebSocket 기반 실시간 갱신 기능 (출동/복귀 시 반영)

## 6. 사용자 인터페이스(UI)
| 항목              | 구현 상태             |
| --------------- | ----------------- |
| 비전문가 사용 가능      | ✅ HTML 폼 UI 구성 완료 |
| 시도/소방서/차량 필터 조회 | ❌ 미구현             |
| 즉시 조회/출력 기능     | ❌ 미구현             |

* 📌 TODO: 검색 필터 + PDF 또는 표 출력 기능 추가

## 7. 기타 고급 기능
| 항목                    | 구현 상태      |
| --------------------- | ---------- |
| 다른 차량 복귀 + 동시에 출동 처리  | ❌ 미구현      |
| 장소/임무별 쿼리 기능          | ❌ 미구현      |
| DB 구조로 AVL, PS-LTE 관리 | ✅ 부분 구현 완료 |

* 📌 TODO: 필드 정제 및 mission/location 컬럼 도입 고려

