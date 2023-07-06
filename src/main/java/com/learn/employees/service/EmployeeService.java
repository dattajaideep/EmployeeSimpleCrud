package com.learn.employees.service;

import com.learn.employees.bean.EmployeeBean;
import com.learn.employees.exception.NoEmployeeFoundException;

import java.util.List;

public interface EmployeeService {
    //    public String addEmployee(EmployeeBean employeeBean);
    List<EmployeeBean> showAllEmployees();

    List<EmployeeBean> showEmployeeById(int id);

    List<EmployeeBean> showEmployeeBySalary(int salary);

    String updateEmployeeById(int id, EmployeeBean employeeBean);

    String deleteEmployeeById(int id) throws NoEmployeeFoundException;

    List<EmployeeBean> findEmployeeLikeName(String name);
}
