package com.learn.employees.controller;

import com.learn.employees.bean.EmployeeBean;
import com.learn.employees.bean.WeatherApiBean;
import com.learn.employees.exception.NoEmployeeFoundException;
import com.learn.employees.exception.ResourceNotFoundException;
import com.learn.employees.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author jaideepvaranasi
 */
@RestController
@RequestMapping("/department")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
//    @PostMapping("/employee/add")
//    public ResponseEntity<String> addEmployee(@RequestBody EmployeeBean employeeBean){
//            String msg=employeeServiceImplementation.addEmployee(employeeBean);
//            return new ResponseEntity<String>(msg, HttpStatus.OK);
//        }

    /**
     *
     * @return
     */
    @GetMapping("/employee/find/all")
    public ResponseEntity<List<EmployeeBean>> allEmployee() {
        List<EmployeeBean> msg = employeeService.showAllEmployees();
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    /**
     *
     * @param id
     * @return
     */
    @PostMapping("/employee/findbyid/all")//sorted order is problem
    public ResponseEntity<List<EmployeeBean>> allEmployeeById(@RequestParam int id) {
        List<EmployeeBean> msg = employeeService.showEmployeeById(id);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    /**
     *
     * @param salary
     * @return
     */
    @PostMapping("/employee/findbysalary/all")
    public ResponseEntity<List<EmployeeBean>> allEmployeeBySalary(@RequestParam int salary) {
        List<EmployeeBean> msg = employeeService.showEmployeeBySalary(salary);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    /**
     *
     * @param id
     * @param employeeBean
     * @return
     */
    @PutMapping("/employee/update/{id}")
    public ResponseEntity<String> updateEmployeeById(@PathVariable("id") int id, @RequestBody EmployeeBean employeeBean) {
        String msg = employeeService.updateEmployeeById(id, employeeBean);
        return new ResponseEntity<String>(msg, HttpStatus.OK);
    }

    /**
     *
     * @param id
     * @return
     * @throws NoEmployeeFoundException
     */
    @DeleteMapping("/employee/delete/{id}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable("id") int id) throws NoEmployeeFoundException {
        String msg = employeeService.deleteEmployeeById(id);
        return new ResponseEntity<String>(msg, HttpStatus.OK);
    }

    /**
     *
     * @param name
     * @return
     */
    @GetMapping("/employee/{name}/alllikename")
    public ResponseEntity<List<EmployeeBean>> allEmployeeLikeName(@PathVariable String name) {
        List<EmployeeBean> msg = employeeService.findEmployeeLikeName(name);
        return new ResponseEntity<List<EmployeeBean>>(msg, HttpStatus.OK);
    }
    @GetMapping("/employee/weatherdetails/{empId}")
    public ResponseEntity<WeatherApiBean> weatherDetailsByEmpId(@PathVariable int empId, @RequestHeader HttpHeaders headers, HttpServletRequest httpServletRequest) throws ResourceNotFoundException {
        WeatherApiBean response = employeeService.getWeatherDetailsByEmpIdAndIpaddress(empId,headers,httpServletRequest);
        return new ResponseEntity<WeatherApiBean>(response,HttpStatus.OK);
    }
}
