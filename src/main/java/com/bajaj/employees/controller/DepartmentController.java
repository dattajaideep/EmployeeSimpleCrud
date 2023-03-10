package com.bajaj.employees.controller;

import com.bajaj.employees.exception.NoDepartmentNameFoundException;
import com.bajaj.employees.service.DepartmentServiceImplementation;
import com.bajaj.employees.bean.DepartmentBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/department")
public class DepartmentController {
    @Autowired
    public DepartmentServiceImplementation departmentServiceImplementation;
    @PostMapping("/add")
    public ResponseEntity<String> addDepartment(@RequestBody DepartmentBean departmentBean) throws NoDepartmentNameFoundException {
        String msg = departmentServiceImplementation.addDepartment(departmentBean);
        return new ResponseEntity<String>(msg, HttpStatus.OK);
    }
    @GetMapping("/find/alldeptwithemp")
    public ResponseEntity<List<DepartmentBean >> showDepartmentDetailsEmployees(){
        List<DepartmentBean> msg=departmentServiceImplementation.findAllDepartmentDetailsEmployees();
        return new ResponseEntity<List<DepartmentBean>>(msg,HttpStatus.OK);
    }
}
