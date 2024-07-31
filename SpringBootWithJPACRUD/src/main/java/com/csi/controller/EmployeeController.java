package com.csi.controller;


import com.csi.model.Employee;
import com.csi.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    private EmployeeServiceImpl employeeServiceImpl;

    @PostMapping("/signup")
    public ResponseEntity<Employee> signUp(@RequestBody Employee employee){
        return ResponseEntity.ok(employeeServiceImpl.signUp(employee));
    }

    @GetMapping("/signin/{empEmailId}/{empPassword}")
    public ResponseEntity<Boolean> signIn(@PathVariable String empEmailId, @PathVariable String empPassword){
        return ResponseEntity.ok(employeeServiceImpl.signIn(empEmailId, empPassword));
    }

    @GetMapping("/findbyid/{empId}")
    public ResponseEntity<Optional<Employee>> findByid(@PathVariable int empId){
        return ResponseEntity.ok(employeeServiceImpl.findById(empId));
    }

    @GetMapping("/findall")

    public  ResponseEntity<List<Employee>>findAll(){
        return ResponseEntity.ok(employeeServiceImpl.findAll());
    }

    @PutMapping("/update/{empId}")

    public  ResponseEntity<Employee>update(@RequestBody Employee employee, @PathVariable int empId){



        return ResponseEntity.ok(employeeServiceImpl.update(employee));
    }
    @GetMapping("/sortbyname")
    public ResponseEntity <List<Employee>> sortByName(){
        return ResponseEntity.ok(employeeServiceImpl.findAll().stream().sorted(Comparator.comparing(emp->emp.getEmpName())).toList());
    }

    @GetMapping("/sortbysalary")
    public ResponseEntity<List<Employee>>sortbysalary(){
        return  ResponseEntity.ok(employeeServiceImpl.findAll().stream().sorted(Comparator.comparingDouble(emp->emp.getEmpSalary())).toList());
    }

    @DeleteMapping("/deletebyid/{empId}")
    public ResponseEntity<String> deleteById(@PathVariable int empId){
        employeeServiceImpl.deleteById(empId);
        return  ResponseEntity.ok("ID DELETED SUCCESSFULLY");
    }

    @DeleteMapping("/deleteall")
    public ResponseEntity<String> deleteAll(){
        employeeServiceImpl.deleteAll();
        return ResponseEntity.ok("ALL DATA DELETED SUCCESSFULLY");
    }




}
