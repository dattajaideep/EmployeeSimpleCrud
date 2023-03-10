package com.bajaj.employees.service;

import com.bajaj.employees.bean.EmployeeBean;
import com.bajaj.employees.dao.DepartmentDao;
import com.bajaj.employees.dao.EmployeeDao;
import com.bajaj.employees.entity.EmployeeEntity;
import com.bajaj.employees.exception.NoEmployeeFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImplementation implements EmployeeService{
    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private DepartmentDao departmentDao;

//    public String addEmployee(EmployeeBean employeeBean) {
//        EmployeeEntity employeeEntity = new EmployeeEntity(12, 12l, "Jaideep", "Role");
//        BeanUtils.copyProperties(employeeBean, employeeEntity);
//        employeeDao.save(employeeEntity);
//        System.out.println("Employee Added");
//        return "Employee Added";
//    }

    @Override
    public List<EmployeeBean> showAllEmployees() {

        ArrayList<EmployeeBean> employeeList =new ArrayList<>();
        List<EmployeeEntity> it =employeeDao.findAll();
        it.forEach(entity ->{
            EmployeeBean employeeBean =new EmployeeBean();
            BeanUtils.copyProperties(entity,employeeBean);
            if(employeeBean.getEmployeeStatus().equalsIgnoreCase("active")) {
                employeeList.add(employeeBean);
            }
        });

        return  employeeList;
    }

    @Override
    public List<EmployeeBean> showEmployeeById(int id) {
        List<EmployeeBean> employeeList=new ArrayList<>();
        Iterable<EmployeeEntity> iterable=employeeDao.findAllById(Collections.singleton(id));
        iterable.forEach(employeeEntity -> {
            if(employeeEntity.getEmployeeStatus().equalsIgnoreCase("active")) {
                EmployeeBean employeeBean = new EmployeeBean();
                BeanUtils.copyProperties(employeeEntity, employeeBean);
                employeeList.add(employeeBean);
            }
        });
        return employeeList;
    }
    @Override
    public List<EmployeeBean> showEmployeeBySalary(int salary) {
        List<EmployeeBean> employeeList=new ArrayList<>();
        Iterable<EmployeeEntity> iterable=employeeDao.findEmployeeBySalary(salary);
        iterable.forEach(employeeEntity -> {
            if(employeeEntity.getEmployeeStatus().equalsIgnoreCase("active")) {
                EmployeeBean employeeBean = new EmployeeBean();
                BeanUtils.copyProperties(employeeEntity, employeeBean);
                employeeList.add(employeeBean);
            }
        });
        return employeeList;
    }

    @Override
    public String updateEmployeeById(int id,EmployeeBean employeeBean){
        String msg= null;
        try {
            Optional<EmployeeEntity> employeeEntity=employeeDao.findById(id);
            msg = "";
            if (employeeEntity.isPresent()) {
                    EmployeeEntity employee = employeeEntity.get();
                if (employee.getEmployeeStatus().equalsIgnoreCase("active")) {
                    employee.setEmployeeSalary(employeeBean.getEmployeeSalary());
                    employee.setEmployeeMobile(employeeBean.getEmployeeMobile());
                    employee.setEmployeeRole(employeeBean.getEmployeeRole());
                    employee.setEmployeeName(employeeBean.getEmployeeName());
                    employeeDao.save(employee);
                    msg = "Employee with id " + id + " has been updated";
                } else {
                    msg = "No such employee found(Deleted)";
                }
            }
            else {
                msg = "No such employee found";

            }
        } catch (NullPointerException e) {
            msg="No such employee found(Null Pointer)";
        }
        return msg;
    }
    public String deleteEmployeeById(int id) throws NoEmployeeFoundException {
        String msg= null;
        try {
            Optional<EmployeeEntity> employeeEntity=employeeDao.findById(id);
            msg = "";
            if(employeeEntity.isPresent()){
                EmployeeEntity employee=employeeEntity.get();
                if(employee.getEmployeeStatus().equalsIgnoreCase("active")){
                    employee.setEmployeeStatus("inactive");
                    employeeDao.save(employee);
                    msg="Successfully deleted";
                }
                else msg="No such employee found(Deleted)";
            }
            else msg="No such employee found";
        } catch (NullPointerException e) {
            msg="No such employee found(Null Pointer)";
        }
        return msg;
    }

    @Override
    public List<EmployeeBean> showEmployeeStartsWith() {
        return null;
    }
}
