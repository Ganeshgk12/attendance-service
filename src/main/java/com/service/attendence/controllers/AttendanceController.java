package com.service.attendence.controllers;

import com.service.attendence.models.Attendance;
import com.service.attendence.services.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api-attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @PostMapping("create-daily-attendance")
    public ResponseEntity<List<Attendance>> createAttendance() {
        return attendanceService.createAttendance();
    }

    @PutMapping("mark-attendance-present")
    public ResponseEntity<String> markAttendancePresent(Long employeeId) {
        if (employeeId == null) {
            return ResponseEntity.badRequest().body("Employee ID cannot be null");
        }
        return attendanceService.markAttendancePresent(employeeId);
    }

    @PutMapping("mark-attendance-absent")
    public ResponseEntity<String> markAttendanceAbsent(Long employeeId) {
        if (employeeId == null) {
            return ResponseEntity.badRequest().body("Employee ID cannot be null");
        }
        return attendanceService.markAttendanceAbsent(employeeId);
    }

    @PutMapping("mark-attendance-leave")
    public ResponseEntity<String> markAttendanceLeave(Long employeeId) {
        if (employeeId == null) {
            return ResponseEntity.badRequest().body("Employee ID cannot be null");
        }
        return attendanceService.markAttendanceLeave(employeeId);
    }
}
