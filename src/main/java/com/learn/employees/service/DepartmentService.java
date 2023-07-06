package com.learn.employees.service;

import com.learn.employees.bean.DepartmentBean;
import com.learn.employees.exception.NoDepartmentNameFoundException;

import java.util.List;

public interface DepartmentService {
    String addDepartment(DepartmentBean departmentBean) throws NoDepartmentNameFoundException;

    List<DepartmentBean> findAllDepartmentDetailsEmployees();

    List<DepartmentBean> findById(int id);

    List<DepartmentBean> findEmployeeDepartmentById(int id);
}
