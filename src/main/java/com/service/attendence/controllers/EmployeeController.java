package com.service.attendence.controllers;

import com.service.attendence.models.Employee;
import com.service.attendence.services.EmployeeService;
import com.service.attendence.wrappers.CreateEmpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api-employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/create-employee")
    public ResponseEntity<String> createEmployee(@RequestBody CreateEmpRequest createEmpRequest) {
        if (createEmpRequest == null || createEmpRequest.getName() == null || createEmpRequest.getEmail() == null
                || createEmpRequest.getDepartment() == null || createEmpRequest.getPosition() == null
                || createEmpRequest.getPhoneNumber() == null)
        {
            return ResponseEntity.badRequest().body("Invalid employee data");
        }
        return employeeService.createEmployee(createEmpRequest);
    }

    @GetMapping("/get-all-employee")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return employeeService.getAllEmployees();
    }
}
