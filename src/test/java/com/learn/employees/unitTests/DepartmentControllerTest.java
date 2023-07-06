package com.learn.employees.unitTests;

import com.learn.employees.bean.DepartmentBean;
import com.learn.employees.controller.DepartmentController;
import com.learn.employees.entity.DepartmentEntity;
import com.learn.employees.entity.EmployeeEntity;
import com.learn.employees.service.DepartmentServiceImplementation;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {
    @MockBean
    public DepartmentServiceImplementation departmentServiceImplementation;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    private Set<EmployeeEntity> employeeEntitySet;
    private DepartmentBean bean;

    public DepartmentControllerTest() {

    }

    @BeforeEach
    public void setUpBean() {
        employeeEntitySet = new HashSet<>();
        employeeEntitySet.add(new EmployeeEntity(101, 12000, 9100820659L, "Myself", "None", "jaibajajdeep@gmail.com", "Telangana", "active"));
        employeeEntitySet.add(new EmployeeEntity(102, 11000, 9100353659L, "Mother", "COO", "jaibajajdeep@gmail.com", "Pune", "active"));
        employeeEntitySet.add(new EmployeeEntity(103, 10000, 9121408873L, "GoSeeInMyContacts", "CEO", "jaibajajdeep@gmail.com", "Goa", "inactive"));
        bean = new DepartmentBean(101, "Hara Teja", employeeEntitySet);
    }

    @Test
    void addDepartmentTest() throws Exception {
        List<DepartmentBean> list = new ArrayList<>();
        list.add(bean);
        when(departmentServiceImplementation.addDepartment(bean)).thenReturn(objectMapper.writeValueAsString("Home"));
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/department/add").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bean)))
//                .andExpect(content().string("Home"))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    void showDepartmentDetailsEmployeesTest() throws Exception {
        DepartmentEntity departmentEntity = new DepartmentEntity(101, "Hara", null);
        List<DepartmentEntity> list = new ArrayList<>();
        list.add(departmentEntity);

        Set<EmployeeEntity> employeeEntities=new HashSet<>();
        employeeEntities.add(new EmployeeEntity(11,11,10L,"Name","Employee","jaiBajajDeep@gmail.com","Line 1","active"));

        DepartmentBean bean = new DepartmentBean(123,"Name",employeeEntities);
        List<DepartmentBean> listBean = new ArrayList<>();
        listBean.add(bean);
        when(departmentServiceImplementation.findAllDepartmentDetailsEmployees()).thenReturn(listBean);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/department/find/alldeptwithemp"))
                .andReturn();
        int status = result.getResponse().getStatus();
        assertEquals(200, status);
    }
}
