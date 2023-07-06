package com.learn.employees.unitTests;

import com.learn.employees.bean.EmployeeBean;
import com.learn.employees.controller.EmployeeController;
import com.learn.employees.service.EmployeeServiceImplementation;
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
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {
    private final List<EmployeeBean> employeeBeanList = new ArrayList<>();
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private EmployeeServiceImplementation employeeServiceImplementation;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUpBean() {
        EmployeeBean employeeBean1 = new EmployeeBean();
        employeeBean1.setEmployeeId(2000163);
        employeeBean1.setEmployeeMobile(9000257219L);
        employeeBean1.setEmployeeSalary(35000);
        employeeBean1.setEmployeeStatus("active");
        employeeBean1.setEmployeeName("Hara Teja");
        employeeBean1.setEmployeeRole("Intern");
        employeeBean1.setEmployeeCreatedDate(null);
        EmployeeBean employeeBean2 = new EmployeeBean();
        employeeBean2.setEmployeeId(2000161);
        employeeBean2.setEmployeeMobile(9121408873L);
        employeeBean2.setEmployeeSalary(35000);
        employeeBean2.setEmployeeStatus("inactive");
        employeeBean2.setEmployeeName("Datta Sai Jaideep");
        employeeBean2.setEmployeeRole("Intern");
        employeeBean2.setEmployeeCreatedDate(null);
        EmployeeBean employeeBean3 = new EmployeeBean();
        employeeBean3.setEmployeeId(2000162);
        employeeBean3.setEmployeeMobile(9992228881L);
        employeeBean3.setEmployeeSalary(35000);
        employeeBean3.setEmployeeStatus("active");
        employeeBean3.setEmployeeName("Aditya");
        employeeBean3.setEmployeeRole("Intern");
        employeeBean3.setEmployeeCreatedDate(null);
        employeeBeanList.add(employeeBean1);
        employeeBeanList.add(employeeBean2);
        employeeBeanList.add(employeeBean3);

    }

    @Test
    void allEmployeeTest() throws Exception {
        when(employeeServiceImplementation.showAllEmployees()).thenReturn(employeeBeanList);
        mockMvc.perform(MockMvcRequestBuilders.get("/department/employee/find/all"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(objectMapper.writeValueAsString(employeeBeanList))));
    }

    @Test
    void allEmployeeByIdTest() throws Exception {
        int id = 2000161;
        List<EmployeeBean> listForTestId = new ArrayList<>();
        listForTestId.add(employeeBeanList.get(1));
        when(employeeServiceImplementation.showEmployeeById(id)).thenReturn(listForTestId);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/department/employee/findbyid/all")
                        .param("id", "2000161"))
                .andExpect(status().isOk())
                .andReturn();
        String resultBody = result.getResponse().getContentAsString();
        assertEquals(objectMapper.writeValueAsString(listForTestId), resultBody);
    }

    @Test
    void allEmployeeBySalary() throws Exception {
        int salary = 35000;
        when(employeeServiceImplementation.showEmployeeBySalary(salary)).thenReturn(employeeBeanList);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/department/employee/findbysalary/all")
                        .param("salary", "35000"))
                .andExpect(status().isOk())
                .andReturn();
        String resultBody = result.getResponse().getContentAsString();
        assertEquals(objectMapper.writeValueAsString(employeeBeanList), resultBody);
    }

    @Test
    void updateEmployeeByIdTest() throws Exception {
        int id = 2000161;
        EmployeeBean employeeBean = new EmployeeBean(2000161, 40000, 9100820659L, "Jaideep", null, null, null, null,null);
        when(employeeServiceImplementation.updateEmployeeById(id, employeeBean)).thenReturn("Hello");
        mockMvc.perform(MockMvcRequestBuilders.put("/department/employee/update/{id}", "2000161")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employeeBean)))
                .andExpect(status().isOk());
//                .andExpect(content().string("Hello"));

    }

    @Test
    void deleteEmployeeByIdTest() throws Exception {
        int id = 2000162;
        when(employeeServiceImplementation.deleteEmployeeById(id)).thenReturn("Deleted");
        mockMvc.perform(MockMvcRequestBuilders.delete("/department/employee/delete/{id}", id))
                .andExpect(content().string(containsString("Deleted")));
    }
}
