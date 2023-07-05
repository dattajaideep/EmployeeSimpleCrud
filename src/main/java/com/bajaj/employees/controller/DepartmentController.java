package com.bajaj.employees.controller;

import com.bajaj.employees.bean.DepartmentBean;
import com.bajaj.employees.exception.NoDepartmentNameFoundException;
import com.bajaj.employees.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/department")
public class DepartmentController {
    @Autowired
    public DepartmentService departmentService;

    @PostMapping("/add")
    public ResponseEntity<String> addDepartment(@RequestBody DepartmentBean departmentBean) throws NoDepartmentNameFoundException {
        String msg = departmentService.addDepartment(departmentBean);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @GetMapping("/find/alldeptwithemp")
    public ResponseEntity<List<DepartmentBean>> showDepartmentDetailsEmployees() {
        List<DepartmentBean> msg = departmentService.findAllDepartmentDetailsEmployees();
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @GetMapping("/find/empdeptbyid/{id}")
    public ResponseEntity<List<DepartmentBean>> findEmployeeDepartmentById(@PathVariable("id") int id) {
        List<DepartmentBean> msg = departmentService.findEmployeeDepartmentById(id);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }
}
