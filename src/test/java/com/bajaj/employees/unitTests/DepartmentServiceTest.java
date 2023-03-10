package com.bajaj.employees.unitTests;

import com.bajaj.employees.bean.DepartmentBean;
import com.bajaj.employees.dao.DepartmentDao;
import com.bajaj.employees.entity.DepartmentEntity;
import com.bajaj.employees.entity.EmployeeEntity;
import com.bajaj.employees.exception.NoDepartmentNameFoundException;
import com.bajaj.employees.service.DepartmentServiceImplementation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {
    @InjectMocks
    DepartmentServiceImplementation departmentServiceImplementation;
    @Mock
    DepartmentDao departmentDao;

    @Test
    public void addDepartmentWithoutExceptionTest() throws NoDepartmentNameFoundException {
        Set<EmployeeEntity> employeeEntitySet=null;
        DepartmentBean departmentBean=new DepartmentBean(101,"HaraTeja",null);
        DepartmentEntity departmentEntity=new DepartmentEntity();
        String response=departmentServiceImplementation.addDepartment(departmentBean);
        assertEquals("Department Added",response);
    }
    @Test
    public void addDepartmentWithExceptionTest() throws NoDepartmentNameFoundException {
        DepartmentBean departmentBean=new DepartmentBean(101,null,null);
//        String response=departmentServiceImplementation.addDepartment(departmentBean);
        assertThrows(NoDepartmentNameFoundException.class,() -> departmentServiceImplementation.addDepartment(departmentBean));
//        assertEquals("No Department Name Found Exception",response);
    }
    @Test
    public void findAllDepartmentDetailsEmployeesTest(){
        Set<EmployeeEntity> employeeBeanSet=new HashSet<>();
        employeeBeanSet.add(new EmployeeEntity(101,12000, 9100820659L,"Myself","None","active",null));
        employeeBeanSet.add(new EmployeeEntity(102,11000, 9100353659L,"Mother","COO","active",null));
        employeeBeanSet.add(new EmployeeEntity(103,10000, 9121408873L,"GoSeeInMyContacts","CEO","inactive",null));
        DepartmentEntity departmentEntity=new DepartmentEntity(101,"Hara Teja",employeeBeanSet);
        List<DepartmentEntity> list=new ArrayList<>();
        List<DepartmentBean> list2=new ArrayList<>();
        list.add(departmentEntity);
        when(departmentDao.findAllActive()).thenReturn(list);
        List<DepartmentBean> response=departmentServiceImplementation.findAllDepartmentDetailsEmployees();
        list.forEach(entity ->{
            DepartmentBean departmentBean =new DepartmentBean();
            BeanUtils.copyProperties(entity,departmentBean);
            list2.add(departmentBean);
        });
        assertEquals(list2.toString(),response.toString());
    }
}
