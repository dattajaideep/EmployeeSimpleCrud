package com.bajaj.employees.unitTests;

import com.bajaj.employees.bean.EmployeeBean;
import com.bajaj.employees.dao.EmployeeDao;
import com.bajaj.employees.entity.EmployeeEntity;
import com.bajaj.employees.exception.NoEmployeeFoundException;
import com.bajaj.employees.helper.EmpDeptSpec;
import com.bajaj.employees.service.EmployeeServiceImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {
    @InjectMocks
    EmployeeServiceImplementation employeeServiceImplementation;
    @Mock
    EmployeeDao employeeDao;
    @Mock
    EmpDeptSpec empDeptSpec;
    private List<EmployeeEntity> entityList;
    private List<EmployeeBean> beanList;
    private EmployeeEntity entity1;
    private EmployeeEntity entity2;
    private EmployeeEntity entity3;

    @BeforeEach
    public void setUp() {
        entity1 = new EmployeeEntity(101, 12000, 9100820659L, "Myself", "None", "jaibajajdeep@gmail.com", "Telangana", "active");
        entity2 = new EmployeeEntity(102, 11000, 9100353659L, "Mother", "COO", "jaibajajdeep@gmail.com", "Telangana", "active");
        entity3 = new EmployeeEntity(103, 11000, 9121408873L, "GoSeeInMyContacts", "CEO", "jaibajajdeep@gmail.com", "Telangana", "inactive");
        entityList = new ArrayList<>();
        entityList.add(entity1);
        entityList.add(entity2);
        entityList.add(entity3);
        EmployeeBean bean1 = new EmployeeBean(101, 12000, 9100820659L, "Myself", "None", "jaibajajdeep@gmail.com", "Telangana", "active", null);
        EmployeeBean bean2 = new EmployeeBean(102, 11000, 9100353659L, "Mother", "COO", "jaibajajdeep@gmail.com", "Telangana", "active", null);
        EmployeeBean bean3 = new EmployeeBean(103, 11000, 9121408873L, "GoSeeInMyContacts", "CEO", "jaibajajdeep@gmail.com", "Telangana", "inactive", null);
        beanList = new ArrayList<>();
        beanList.add(bean1);
        beanList.add(bean2);
        beanList.add(bean3);
    }

    @Test
    void showAllEmployeeTest() {
        when(employeeDao.findAll()).thenReturn(entityList);
        List<EmployeeBean> response = employeeServiceImplementation.showAllEmployees();
        beanList.remove(2);
        assertEquals(beanList.toString(), response.toString());
    }

    @Test
    void showEmployeeByIdTest() {
        int id = 102;
        when(employeeDao.findAll(Mockito.any(Specification.class))).thenReturn(entityList);
        List<EmployeeBean> response = employeeServiceImplementation.showEmployeeById(id);
        beanList.remove(2);
        assertEquals(beanList.get(0).getEmployeeName(), response.get(0).getEmployeeName());
    }

    @Test
    public void showEmployeeBySalaryTest() {
        int salary = 11000;
        List<EmployeeEntity> entityList1 = new ArrayList<>();
        entityList1.add(entityList.get(1));
        entityList1.add(entityList.get(2));
        when(employeeDao.findAll(any(Specification.class))).thenReturn(entityList1);
        List<EmployeeBean> response = employeeServiceImplementation.showEmployeeBySalary(salary);
        beanList.remove(0);
        beanList.remove(1);
        assertEquals(beanList.toString(), response.toString());
    }

    //Delete method testcases
    @Test
    void deleteShouldEmployeeBeActiveAndPresentTest() throws NoEmployeeFoundException {
        int id = 102;
        when(employeeDao.findOne(any(Specification.class))).thenReturn(Optional.of(entity2));
        String response = employeeServiceImplementation.deleteEmployeeById(id);
        assertEquals("Successfully deleted", response);
    }

    @Test
    void deleteShouldEmployeeBeInactiveAndPresentTest() throws NoEmployeeFoundException {
        int id = 103;
        when(employeeDao.findOne(any(Specification.class))).thenReturn(Optional.of(entity3));
        String response = employeeServiceImplementation.deleteEmployeeById(id);
        assertEquals("No such employee found(Deleted)", response);
    }

    @Test
    void deleteShouldEmployeeBeNotPresentTest1() throws NoEmployeeFoundException {
        int id = 104;
        when(employeeDao.findOne(any(Specification.class))).thenReturn(Optional.empty());
        String response = employeeServiceImplementation.deleteEmployeeById(id);
        assertEquals("No such employee found", response);
    }

    @Test
    void deleteShouldEmployeeBeNotPresentTest2() throws NoEmployeeFoundException {
        int id = 104;
        when(employeeDao.findOne(any(Specification.class))).thenReturn(null);
        String response = employeeServiceImplementation.deleteEmployeeById(id);
        assertEquals("No such employee found(Null Pointer)", response);
    }

    //Update Method Testcases
    @Test
    void updateShouldEmployeeBeActiveAndPresentTest() {
        int id = 102;
        EmployeeBean beanUpdate = new EmployeeBean();
        when(employeeDao.findById(id)).thenReturn(Optional.of(entity2));
        String response = employeeServiceImplementation.updateEmployeeById(id, beanUpdate);
        assertEquals("Employee with id " + id + " has been updated", response);
    }

    @Test
    void updateShouldEmployeeBeInactiveAndPresentTest() {
        int id = 103;
        EmployeeBean beanUpdate = new EmployeeBean(103, 11000, 9121408873L,
                "GoSeeInMyContacts", "CEO", "jaibajajdeep@gmail.com", "Telangana", "active", null);
        when(employeeDao.findById(id)).thenReturn(Optional.of(entity3));
        String response = employeeServiceImplementation.updateEmployeeById(id, beanUpdate);
        assertEquals("No such employee found(Deleted)", response);
    }

    @Test
    void updateShouldEmployeeBeNotPresentTest1() {
        int id = 104;
        EmployeeBean beanUpdate = new EmployeeBean(103, 11000, 9121408873L,
                "GoSeeInMyContacts", "CEO", "jaibajajdeep@gmail.com", "Telangana", "active", null);
        when(employeeDao.findById(id)).thenReturn(Optional.empty());
        String response = employeeServiceImplementation.updateEmployeeById(id, beanUpdate);
        assertEquals("No such employee found", response);
    }

    @Test
    void updateShouldEmployeeBeNotPresentTest2() {
        int id = 104;
        EmployeeBean beanUpdate = new EmployeeBean(103, 11000, 9121408873L,
                "GoSeeInMyContacts", "CEO", "jaibajajdeep@gmail.com", "Telangana", "active", null);
        when(employeeDao.findById(id)).thenReturn(null);
        String response = employeeServiceImplementation.updateEmployeeById(id, beanUpdate);
        assertEquals("No such employee found(Null Pointer)", response);
    }
}
