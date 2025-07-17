package com.service.attendence.services;

import com.service.attendence.models.Attendance;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AttendanceService {
    ResponseEntity<List<Attendance>> createAttendance();

    ResponseEntity<String> markAttendancePresent(Long employeeId);

    ResponseEntity<String> markAttendanceAbsent(Long employeeId);

    ResponseEntity<String> markAttendanceLeave(Long employeeId);
}
