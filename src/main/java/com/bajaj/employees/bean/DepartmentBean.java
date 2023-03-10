package com.bajaj.employees.bean;

import com.bajaj.employees.entity.EmployeeEntity;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentBean {
    private int departmentId;
    private String departmentName;
    private Set<EmployeeEntity> employeeEntityList;

}
