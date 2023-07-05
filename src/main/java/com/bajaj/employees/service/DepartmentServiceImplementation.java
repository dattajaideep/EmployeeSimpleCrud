package com.bajaj.employees.service;

import com.bajaj.employees.bean.DepartmentBean;
import com.bajaj.employees.bean.SearchCriteria;
import com.bajaj.employees.dao.DepartmentDao;
import com.bajaj.employees.entity.DepartmentEntity;
import com.bajaj.employees.exception.NoDepartmentNameFoundException;
import com.bajaj.employees.helper.EmpDeptSpec;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class DepartmentServiceImplementation implements DepartmentService {
    @Autowired
    private DepartmentDao departmentDao;
    @Autowired
    private EmpDeptSpec empDeptSpec;

    public String addDepartment(DepartmentBean departmentBean) throws NoDepartmentNameFoundException, NullPointerException {
        DepartmentEntity departmentEntity = new DepartmentEntity();
        if (Objects.isNull(departmentBean.getDepartmentName())) {
            throw new NoDepartmentNameFoundException("No Department Name Found Exception");
        } else {
            BeanUtils.copyProperties(departmentBean, departmentEntity);
            departmentDao.save(departmentEntity);
            System.out.println("Department Added");
            return "Department Added";
        }
    }

    @Override
    public List<DepartmentBean> findAllDepartmentDetailsEmployees() {

        List<DepartmentBean> departmentList = new ArrayList<>();
//        List<DepartmentEntity> it =departmentDao.findAll();
        List<DepartmentEntity> it = departmentDao.findAllActive();//Unless all employees are inactive the department did not become inactive
        it.forEach(entity -> {
            DepartmentBean departmentBean = new DepartmentBean();
            BeanUtils.copyProperties(entity, departmentBean);
            departmentList.add(departmentBean);
        });
        System.out.println(departmentList);
        return departmentList;
    }

    @Override
    public List<DepartmentBean> findById(int id) {
        return null;
    }

    @Override
    public List<DepartmentBean> findEmployeeDepartmentById(int id) {
        EmpDeptSpec empDeptSpec = new EmpDeptSpec();
        List<DepartmentBean> listBean = new ArrayList<>();
        List<DepartmentEntity> departmentEntityList =
                departmentDao.findEmployeeDepartmentById(id);
        empDeptSpec.add(new SearchCriteria());
        empDeptSpec.add(new SearchCriteria());
//                departmentDao.findAll(empDeptSpec.add(new SearchCriteria("id",id, SearchOperation.EQUAL)));
        departmentEntityList.forEach(entity -> {
            DepartmentBean departmentBean = new DepartmentBean();
            BeanUtils.copyProperties(entity, departmentBean);
            listBean.add(departmentBean);
        });
        return listBean;
    }


}
