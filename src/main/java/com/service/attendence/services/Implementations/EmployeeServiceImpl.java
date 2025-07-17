package com.service.attendence.services.Implementations;

import com.service.attendence.models.Employee;
import com.service.attendence.repositories.EmployeeRepository;
import com.service.attendence.services.EmployeeService;
import com.service.attendence.utils.Builders;
import com.service.attendence.wrappers.CreateEmpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    private static final Builders builders = new Builders();

    @Override
    public ResponseEntity<String> createEmployee(CreateEmpRequest createEmpRequest) {
        if (employeeRepository.existsByEmail(createEmpRequest.getEmail())
                || employeeRepository.existsByPhoneNumber(createEmpRequest.getPhoneNumber())) {
            return ResponseEntity.badRequest().body("Employee with this email already exists");
        }
        Employee employee = builders.buildEmployee(createEmpRequest);
        employeeRepository.save(employee);
        return ResponseEntity.ok("Employee created successfully with ID: " + employee.getEmployeeId());
    }

    @Override
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        if (employees.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(employees);
    }

    @Override
    public ResponseEntity<Employee> getEmployeeById(Long employeeId) {
        return employeeRepository.findById(employeeId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
