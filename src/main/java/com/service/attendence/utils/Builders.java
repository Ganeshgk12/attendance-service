package com.service.attendence.utils;

import com.service.attendence.models.Attendance;
import com.service.attendence.models.AttendanceStatus;
import com.service.attendence.models.Employee;
import com.service.attendence.wrappers.CreateEmpRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Builders {

     public Employee buildEmployee(CreateEmpRequest createEmpRequest) {
        Employee employee = new Employee();
        employee.setName(createEmpRequest.getName());
        employee.setEmail(createEmpRequest.getEmail());
        employee.setPhoneNumber(createEmpRequest.getPhoneNumber());
        employee.setDepartment(createEmpRequest.getDepartment());
        employee.setPosition(createEmpRequest.getPosition());
        employee.setStatus(Employee.Status.ACTIVE);
        return employee;
    }
    public List<Attendance> buildAttendanceList(List<Employee> attendanceList) {
        return attendanceList.stream()
                .map(employee -> {
                    Attendance attendance = new Attendance();
                    attendance.setEmployeeId(employee.getEmployeeId());
                    attendance.setEmployeeName(employee.getName());
                    attendance.setCheckInTime(null);
                    attendance.setCheckOutTime(null);
                    attendance.setAttendanceDate(LocalDate.now());
                    attendance.setStatus(AttendanceStatus.NOT_PRESENT);
                    return attendance;
                })
                .toList();
    }

}
