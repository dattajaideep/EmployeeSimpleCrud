package com.bajaj.employees.service;

import com.bajaj.employees.bean.DepartmentBean;
import com.bajaj.employees.exception.NoDepartmentNameFoundException;

import java.util.List;

public interface DepartmentService {
    String addDepartment(DepartmentBean departmentBean) throws NoDepartmentNameFoundException;

    List<DepartmentBean> findAllDepartmentDetailsEmployees();

    List<DepartmentBean> findById(int id);

    List<DepartmentBean> findEmployeeDepartmentById(int id);
}
