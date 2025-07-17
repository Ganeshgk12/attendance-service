package com.service.attendence.repositories;

import com.service.attendence.models.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    Attendance findByEmployeeIdAndAttendanceDate(Long employeeId, LocalDate today);
}
