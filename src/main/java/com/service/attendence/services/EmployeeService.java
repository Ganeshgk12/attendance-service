package com.service.attendence.services;

import com.service.attendence.models.Employee;
import com.service.attendence.wrappers.CreateEmpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {
    ResponseEntity<String> createEmployee(CreateEmpRequest createEmpRequest);

    ResponseEntity<List<Employee>> getAllEmployees();
}
