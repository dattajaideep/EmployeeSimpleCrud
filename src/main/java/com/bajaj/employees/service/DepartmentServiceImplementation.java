package com.bajaj.employees.service;

import com.bajaj.employees.bean.DepartmentBean;
import com.bajaj.employees.dao.DepartmentDao;
import com.bajaj.employees.entity.DepartmentEntity;
import com.bajaj.employees.exception.NoDepartmentNameFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentServiceImplementation implements DepartmentService {
    @Autowired
    private DepartmentDao departmentDao;
    public String addDepartment(DepartmentBean departmentBean) throws NoDepartmentNameFoundException, NullPointerException {
        DepartmentEntity departmentEntity = new DepartmentEntity();
            if(departmentBean.getDepartmentName()==null){
                throw new NoDepartmentNameFoundException("No Department Name Found Exception");
            }
            else {
                BeanUtils.copyProperties(departmentBean, departmentEntity);
                departmentDao.save(departmentEntity);
                System.out.println("Department Added");
                return "Department Added";
            }
    }

    @Override
    public List<DepartmentBean> findAllDepartmentDetailsEmployees() {

        List<DepartmentBean> departmentList =new ArrayList<>();
//        List<DepartmentEntity> it =departmentDao.findAll();
        List<DepartmentEntity> it = departmentDao.findAllActive();//Unless all employees are inactive the department did not become inactive
        it.forEach(entity ->{
            DepartmentBean departmentBean =new DepartmentBean();
            BeanUtils.copyProperties(entity,departmentBean);
            departmentList.add(departmentBean);
        });
        System.out.println(departmentList);
        return  departmentList;
    }

    @Override
    public List<DepartmentBean> findById(int id) {
        return null;
    }

    @Override
    public List<DepartmentBean> findByEmployeeDepartmentById(int id) {
        return null;
    }


}
