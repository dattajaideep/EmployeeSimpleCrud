package com.learn.employees.unitTests;

import com.learn.employees.bean.DepartmentBean;
import com.learn.employees.dao.DepartmentDao;
import com.learn.employees.entity.DepartmentEntity;
import com.learn.employees.entity.EmployeeEntity;
import com.learn.employees.exception.NoDepartmentNameFoundException;
import com.learn.employees.service.DepartmentServiceImplementation;
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
class DepartmentServiceTest {
    @InjectMocks
    DepartmentServiceImplementation departmentServiceImplementation;
    @Mock
    DepartmentDao departmentDao;

    @Test
    void addDepartmentWithoutExceptionTest() throws NoDepartmentNameFoundException {

        DepartmentBean departmentBean = new DepartmentBean(101, "HaraTeja",null);

        String response = departmentServiceImplementation.addDepartment(departmentBean);
        assertEquals("Department Added", response);
    }

    @Test
    void addDepartmentWithExceptionTest() {
        DepartmentBean departmentBean = new DepartmentBean(101, null, null);
//        String response=departmentServiceImplementation.addDepartment(departmentBean);
        assertThrows(NoDepartmentNameFoundException.class, () -> departmentServiceImplementation.addDepartment(departmentBean));
//        assertEquals("No Department Name Found Exception",response);
    }

    @Test
    void findAllDepartmentDetailsEmployeesTest() {
        Set<EmployeeEntity> employeeBeanSet = new HashSet<>();
        employeeBeanSet.add(new EmployeeEntity(101, 12000, 9100820659L, "Myself", "None", "jaibajajdeep@gmail.com", "Telangana", "active"));
        employeeBeanSet.add(new EmployeeEntity(102, 11000, 9100353659L, "Mother", "COO", "jaibajajdeep@gmail.com", "Telangana", "active"));
        employeeBeanSet.add(new EmployeeEntity(103, 10000, 9121408873L, "GoSeeInMyContacts", "CEO", "jaibajajdeep@gmail.com", "Telangana", "inactive"));
        DepartmentEntity departmentEntity = new DepartmentEntity(101, "Hara Teja", employeeBeanSet);
        List<DepartmentEntity> list = new ArrayList<>();
        List<DepartmentBean> list2 = new ArrayList<>();
        list.add(departmentEntity);
        when(departmentDao.findAllActive()).thenReturn(list);
        List<DepartmentBean> response = departmentServiceImplementation.findAllDepartmentDetailsEmployees();
        list.forEach(entity -> {
            DepartmentBean departmentBean = new DepartmentBean();
            BeanUtils.copyProperties(entity, departmentBean);
            list2.add(departmentBean);
        });
        assertEquals(list2.toString(), response.toString());
    }
}
