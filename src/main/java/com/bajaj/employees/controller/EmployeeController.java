package com.bajaj.employees.controller;

import com.bajaj.employees.bean.EmployeeBean;
import com.bajaj.employees.exception.NoEmployeeFoundException;
import com.bajaj.employees.service.EmployeeServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class EmployeeController {
    @Autowired
    EmployeeServiceImplementation employeeServiceImplementation;
//    @PostMapping("/employee/add")
//    public ResponseEntity<String> addEmployee(@RequestBody EmployeeBean employeeBean){
//            String msg=employeeServiceImplementation.addEmployee(employeeBean);
//            return new ResponseEntity<String>(msg, HttpStatus.OK);
//        }

    @GetMapping("/employee/find/all")
    public ResponseEntity<List<EmployeeBean>> allEmployee(){
        List<EmployeeBean> msg=employeeServiceImplementation.showAllEmployees();
        return new ResponseEntity<List<EmployeeBean>>(msg,HttpStatus.OK);
    }
    @PostMapping("/employee/findbyid/all")//sorted order is problem
    public ResponseEntity<List<EmployeeBean>> allEmployeeById(@RequestParam int id){
        List<EmployeeBean> msg=employeeServiceImplementation.showEmployeeById(id);
        return new ResponseEntity<List<EmployeeBean>>(msg,HttpStatus.OK);
    }
    @PostMapping("/employee/findbysalary/all")
    public ResponseEntity<List<EmployeeBean>> allEmployeeBySalary(@RequestParam int salary){
        List<EmployeeBean> msg=employeeServiceImplementation.showEmployeeBySalary(salary);
        return new ResponseEntity<List<EmployeeBean>>(msg,HttpStatus.OK);
    }

    @PutMapping("/employee/update/{id}")
    public ResponseEntity<String> updateEmployeeById(@PathVariable("id") int id,@RequestBody EmployeeBean employeeBean){
            String msg=employeeServiceImplementation.updateEmployeeById(id,employeeBean);
            return new ResponseEntity<String>(msg,HttpStatus.OK);
    }
    @DeleteMapping("/employee/delete/{id}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable("id") int id) throws NoEmployeeFoundException {
            String msg=employeeServiceImplementation.deleteEmployeeById(id);
            return new ResponseEntity<String>(msg,HttpStatus.OK);
    }
}
