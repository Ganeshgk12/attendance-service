package com.service.attendence.services.Implementations;

import com.service.attendence.models.Attendance;
import com.service.attendence.models.AttendanceStatus;
import com.service.attendence.models.Employee;
import com.service.attendence.repositories.AttendanceRepository;
import com.service.attendence.services.AttendanceService;
import com.service.attendence.services.EmployeeService;
import com.service.attendence.utils.Builders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private AttendanceRepository attendanceRepository;

    private static final Builders builders = new Builders();

    @Override
    public ResponseEntity<List<Attendance>> createAttendance() {
        List<Employee> employees = employeeService.getAllEmployees().getBody();
        if (employees == null || employees.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<Attendance> todayAttendance = builders.buildAttendanceList(employees);
        if (todayAttendance.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(todayAttendance);
    }

    @Override
    public ResponseEntity<String> markAttendancePresent(Long employeeId) {
        // Get today attendance of the employee id
        LocalDate today = LocalDate.now();
        Attendance attendance = attendanceRepository.findByEmployeeIdAndAttendanceDate(employeeId, today);
        if (attendance == null) {
            return ResponseEntity.badRequest().body("No attendance record found for employee ID: " + employeeId + " on " + today);
        }
        // Check if attendance is already marked present
        if (attendance.getStatus() == AttendanceStatus.PRESENT) {
            return ResponseEntity.ok("Attendance already marked as present for employee ID: " + employeeId + " on " + today);
        }
        // Mark attendance as present
        attendance.setCheckInTime(LocalDate.now().atStartOfDay());
        attendance.setCheckOutTime(LocalDate.now().atTime(18, 59, 59));
        attendance.setStatus(AttendanceStatus.PRESENT);
        attendanceRepository.save(attendance);
        return ResponseEntity.ok("Attendance marked as present for employee ID: " + employeeId + " on " + today);
    }

    @Override
    public ResponseEntity<String> markAttendanceAbsent(Long employeeId) {
        // Get today attendance of the employee id
        LocalDate today = LocalDate.now();
        Attendance attendance = attendanceRepository.findByEmployeeIdAndAttendanceDate(employeeId, today);
        if (attendance == null) {
            return ResponseEntity.badRequest().body("No attendance record found for employee ID: " + employeeId + " on " + today);
        }
        // Check if attendance is already marked absent
        if (attendance.getStatus() == AttendanceStatus.ABSENT) {
            return ResponseEntity.ok("Attendance already marked as absent for employee ID: " + employeeId + " on " + today);
        }
        // Mark attendance as absent
        attendance.setCheckOutTime(null);
        attendance.setStatus(AttendanceStatus.ABSENT);
        attendanceRepository.save(attendance);
        return ResponseEntity.ok("Attendance marked as absent for employee ID: " + employeeId + " on " + today);
    }

    @Override
    public ResponseEntity<String> markAttendanceLeave(Long employeeId) {
    // Get today attendance of the employee id
        LocalDate today = LocalDate.now();
        Attendance attendance = attendanceRepository.findByEmployeeIdAndAttendanceDate(employeeId, today);
        if (attendance == null) {
            return ResponseEntity.badRequest().body("No attendance record found for employee ID: " + employeeId + " on " + today);
        }
        // Check if attendance is already marked as leave
        if (attendance.getStatus() == AttendanceStatus.ON_LEAVE) {
            return ResponseEntity.ok("Attendance already marked as leave for employee ID: " + employeeId + " on " + today);
        }
        // Mark attendance as leave
        attendance.setCheckOutTime(null);
        attendance.setStatus(AttendanceStatus.ON_LEAVE);
        attendanceRepository.save(attendance);
        return ResponseEntity.ok("Attendance marked as leave for employee ID: " + employeeId + " on " + today);
    }
}
