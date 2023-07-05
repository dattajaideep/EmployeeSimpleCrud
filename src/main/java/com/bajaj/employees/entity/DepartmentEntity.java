package com.bajaj.employees.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
//@ToString
@Builder
@Entity
@Table(name = "department_entity")
public class DepartmentEntity {
    @Id
    @Column(name = "department_id")
    private int departmentId;

    @Column(name = "department_name")
    private String departmentName;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "fk_dept_id", referencedColumnName = "department_id")
    @ToString.Exclude
    private Set<EmployeeEntity> employeeEntityList;

    public DepartmentEntity(String departmentName) throws NullPointerException {
        if (departmentName == null) {
            throw new NullPointerException("Name cannot be null");
        }
        this.departmentName = departmentName;
    }

//    @Column(name ="manager_id")
//    private int managerId;
//    @Column(name = "manager_name")
//    private String managerName;


    public DepartmentEntity() {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        DepartmentEntity that = (DepartmentEntity) o;
        return getDepartmentId() != 0 && Objects.equals(getDepartmentId(), that.getDepartmentId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public DepartmentEntity(int departmentId, String departmentName, Set<EmployeeEntity> employeeEntityList) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.employeeEntityList = employeeEntityList;
    }

    @Override
    public String toString() {
        return "DepartmentEntity{" +
                "departmentId=" + departmentId +
                ", departmentName='" + departmentName + '\'' +
                ", employeeEntityList=" + employeeEntityList +
                '}';
    }
}
