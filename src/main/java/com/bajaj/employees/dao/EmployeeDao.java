package com.bajaj.employees.dao;

import com.bajaj.employees.bean.EmployeeBean;
import com.bajaj.employees.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface EmployeeDao extends JpaRepository<EmployeeEntity,Integer> {
//    @Query("select q.employee_status from employee_entity q where q.employee_id=:id")
//    public boolean checkEmployeeStatus(int id);
    @Query("select q from EmployeeEntity q where q.employeeSalary=:salary")
    List<EmployeeEntity> findEmployeeBySalary(int salary);

//    @Query("SELECT e FROM EmployeeEntity e")
//    List<EmployeeEntity> findAllWithDepartmentId();
}
