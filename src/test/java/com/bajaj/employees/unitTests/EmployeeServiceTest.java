package com.bajaj.employees.unitTests;
import com.bajaj.employees.bean.EmployeeBean;
import com.bajaj.employees.dao.EmployeeDao;
import com.bajaj.employees.entity.EmployeeEntity;
import com.bajaj.employees.exception.NoEmployeeFoundException;
import com.bajaj.employees.service.EmployeeServiceImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
    @InjectMocks
    EmployeeServiceImplementation employeeServiceImplementation;
    @Mock
    EmployeeDao employeeDao;
    private List<EmployeeEntity> entityList;
    private List<EmployeeBean> beanList;
    private EmployeeEntity entity1;
    private EmployeeEntity entity2;
    private EmployeeEntity entity3;
    @BeforeEach
    public void setUp(){
        entity1=new EmployeeEntity(101,12000, 9100820659L,"Myself","None","active",null);
        entity2=new EmployeeEntity(102,11000, 9100353659L,"Mother","COO","active",null);
        entity3=new EmployeeEntity(103,11000, 9121408873L,"GoSeeInMyContacts","CEO","inactive",null);
        entityList=new ArrayList<>();
        entityList.add(entity1);
        entityList.add(entity2);
        entityList.add(entity3);
        EmployeeBean bean1=new EmployeeBean(101,12000, 9100820659L,"Myself","None","active",null);
        EmployeeBean bean2=new EmployeeBean(102,11000, 9100353659L,"Mother","COO","active",null);
        EmployeeBean bean3=new EmployeeBean(103,11000, 9121408873L,"GoSeeInMyContacts","CEO","inactive",null);
        beanList=new ArrayList<>();
        beanList.add(bean1);
        beanList.add(bean2);
        beanList.add(bean3);
    }
    @Test
    public void showAllEmployeeTest(){
        when(employeeDao.findAll()).thenReturn(entityList);
        List<EmployeeBean> response= employeeServiceImplementation.showAllEmployees();
        beanList.remove(2);
        assertEquals(beanList.toString(),response.toString());
    }
    @Test
    public void showEmployeeByIdTest(){
        int id= 102;
        when(employeeDao.findAllById(Collections.singleton(id))).thenReturn(entityList);
        List<EmployeeBean> response= employeeServiceImplementation.showEmployeeById(id);
        beanList.remove(2);
        assertEquals(beanList.toString(),response.toString());
    }
    @Test
    public void showEmployeeBySalaryTest(){
        int salary=11000;
        List<EmployeeEntity> entityList1=new ArrayList<>();
        entityList1.add(entityList.get(1));
        entityList1.add(entityList.get(2));
        when(employeeDao.findEmployeeBySalary(salary)).thenReturn(entityList1);
        List<EmployeeBean> response= employeeServiceImplementation.showEmployeeBySalary(salary);
        beanList.remove(0);
        beanList.remove(1);
        assertEquals(beanList.toString(),response.toString());
    }

    //Delete method testcases
    @Test
    public void deleteShouldEmployeeBeActiveAndPresentTest() throws NoEmployeeFoundException {
        int id= 102;
        when(employeeDao.findById(id)).thenReturn(Optional.of(entity2));
        String response= employeeServiceImplementation.deleteEmployeeById(id);
        assertEquals("Successfully deleted",response);
    }
     @Test
     public void deleteShouldEmployeeBeInactiveAndPresentTest() throws NoEmployeeFoundException {
         int id= 103;
         when(employeeDao.findById(id)).thenReturn(Optional.of(entity3));
         String response= employeeServiceImplementation.deleteEmployeeById(id);
         assertEquals("No such employee found(Deleted)",response);
     }
     @Test
     public void deleteShouldEmployeeBeNotPresentTest1() throws NoEmployeeFoundException {
         int id= 104;
         EmployeeEntity emptyEmployee=new EmployeeEntity();
         when(employeeDao.findById(id)).thenReturn(Optional.empty());
         String response= employeeServiceImplementation.deleteEmployeeById(id);
         assertEquals("No such employee found",response);
     }
     @Test
     public void deleteShouldEmployeeBeNotPresentTest2() throws NoEmployeeFoundException {
         int id= 104;
         when(employeeDao.findById(id)).thenReturn(null);
         String response= employeeServiceImplementation.deleteEmployeeById(id);
         assertEquals("No such employee found(Null Pointer)",response);
     }
    //Update Method Testcases
    @Test
    public void updateShouldEmployeeBeActiveAndPresentTest() throws NoEmployeeFoundException {
        int id= 102;
        EmployeeBean beanUpdate=new EmployeeBean();
        when(employeeDao.findById(id)).thenReturn(Optional.of(entity2));
        String response= employeeServiceImplementation.updateEmployeeById(id,beanUpdate);
        assertEquals("Employee with id "+id+" has been updated",response);
    }

     @Test
     public void updateShouldEmployeeBeInactiveAndPresentTest() throws NoEmployeeFoundException {
         int id= 103;
         EmployeeBean beanUpdate=new EmployeeBean(103,11000, 9121408873L,
                 "GoSeeInMyContacts","CEO","active",null);
         when(employeeDao.findById(id)).thenReturn(Optional.of(entity3));
         String response= employeeServiceImplementation.updateEmployeeById(id,beanUpdate);
         assertEquals("No such employee found(Deleted)",response);
     }
     @Test
     public void updateShouldEmployeeBeNotPresentTest1() throws NoEmployeeFoundException {
         int id=104;
         EmployeeBean beanUpdate=new EmployeeBean(103,11000, 9121408873L,
                 "GoSeeInMyContacts","CEO","active",null);
         when(employeeDao.findById(id)).thenReturn(Optional.empty());
         String response= employeeServiceImplementation.updateEmployeeById(id,beanUpdate);
         assertEquals("No such employee found",response);
     }
     @Test
     public void updateShouldEmployeeBeNotPresentTest2() throws NoEmployeeFoundException {
         int id= 104;
         EmployeeBean beanUpdate=new EmployeeBean(103,11000, 9121408873L,
                 "GoSeeInMyContacts","CEO","active",null);
         when(employeeDao.findById(id)).thenReturn(null);
         String response= employeeServiceImplementation.updateEmployeeById(id,beanUpdate);
         assertEquals("No such employee found(Null Pointer)",response);
     }
}
