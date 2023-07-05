package com.bajaj.employees.bean;

import com.bajaj.employees.entity.EmployeeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
//@ToString
@Builder
//@AllArgsConstructor
//@NoArgsConstructor
public class DepartmentBean {
    private int departmentId;
    private String departmentName;
    private Set<EmployeeEntity> employeeEntityList;

    //Replaced Lombok
    public DepartmentBean(int departmentId, String departmentName, Set<EmployeeEntity> employeeEntityList) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.employeeEntityList = employeeEntityList;
    }

    public DepartmentBean() {
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Set<EmployeeEntity> getEmployeeEntityList() {
        return employeeEntityList;
    }

    public void setEmployeeEntityList(Set<EmployeeEntity> employeeEntityList) {
        this.employeeEntityList = employeeEntityList;
    }

    @Override
    public String toString() {
        return "DepartmentBean{" +
                "departmentId=" + departmentId +
                ", departmentName='" + departmentName + '\'' +
                ", employeeEntityList=" + employeeEntityList +
                '}';
    }
}
