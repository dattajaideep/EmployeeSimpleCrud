package com.learn.employees.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

//@ToString
@Entity
@Table(name = "employee_entity", uniqueConstraints = {@UniqueConstraint(columnNames = {"employeeId"})})
public class EmployeeEntity {
    @Column(name = "employeeCreatedDate")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private static Date employeeCreatedDate;
    @Id
    private int employeeId;
    @Column(name = "employee_salary")
    private int employeeSalary;
    @Column(name = "employee_mobile")
    private Long employeeMobile;
    @Column(name = "employee_name")
    private String employeeName;
    @Column(name = "employee_role")
    private String employeeRole;
    @Column(name = "employee_mail")
    private String employeeMail;
    @Column(name = "employee_address")
    private String employeeAddress;
    @Column(name = "employee_status")
    private String employeeStatus;
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "department_id")
//    private DepartmentEntity departmentEntity;

    public EmployeeEntity(int employeeSalary, long employeeMobile, String employeeName, String employeeRole) {
        this.employeeSalary = employeeSalary;
        this.employeeMobile = employeeMobile;
        this.employeeName = employeeName;
        this.employeeRole = employeeRole;
    }

    public EmployeeEntity(int employeeId, int employeeSalary, Long employeeMobile, String employeeName, String employeeRole) {
        this.employeeId = employeeId;
        this.employeeSalary = employeeSalary;
        this.employeeMobile = employeeMobile;
        this.employeeName = employeeName;
        this.employeeRole = employeeRole;
    }


    public EmployeeEntity(int employeeId, int employeeSalary, Long employeeMobile, String employeeName, String employeeRole, String employeeMail, String employeeAddress, String employeeStatus) {
        this.employeeId = employeeId;
        this.employeeSalary = employeeSalary;
        this.employeeMobile = employeeMobile;
        this.employeeName = employeeName;
        this.employeeRole = employeeRole;
        this.employeeMail = employeeMail;
        this.employeeAddress = employeeAddress;
        this.employeeStatus = employeeStatus;
    }

    public EmployeeEntity() {
    }

    public static Date getEmployeeCreatedDate() {
        return employeeCreatedDate;
    }

    public static void setEmployeeCreatedDate(Date employeeCreatedDate) {
        EmployeeEntity.employeeCreatedDate = employeeCreatedDate;
    }

    @PrePersist
    protected void onCreate() {
        if (employeeStatus == null || employeeStatus.isEmpty()) {
            employeeStatus = "active";
        }
        employeeCreatedDate = new Date();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        EmployeeEntity that = (EmployeeEntity) o;
        return getEmployeeId() != 0 && Objects.equals(getEmployeeId(), that.getEmployeeId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getEmployeeSalary() {
        return employeeSalary;
    }

    public void setEmployeeSalary(int employeeSalary) {
        this.employeeSalary = employeeSalary;
    }

    public Long getEmployeeMobile() {
        return employeeMobile;
    }

    public void setEmployeeMobile(Long employeeMobile) {
        this.employeeMobile = employeeMobile;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeRole() {
        return employeeRole;
    }

    public void setEmployeeRole(String employeeRole) {
        this.employeeRole = employeeRole;
    }

    public String getEmployeeMail() {
        return employeeMail;
    }

    public void setEmployeeMail(String employeeMail) {
        this.employeeMail = employeeMail;
    }

    public String getEmployeeAddress() {
        return employeeAddress;
    }

    public void setEmployeeAddress(String employeeAddress) {
        this.employeeAddress = employeeAddress;
    }

    public String getEmployeeStatus() {
        return employeeStatus;
    }

    public void setEmployeeStatus(String employeeStatus) {
        this.employeeStatus = employeeStatus;
    }

    @Override
    public String toString() {
        return "EmployeeEntity{" +
                "employeeId=" + employeeId +
                ", employeeSalary=" + employeeSalary +
                ", employeeMobile=" + employeeMobile +
                ", employeeName='" + employeeName + '\'' +
                ", employeeRole='" + employeeRole + '\'' +
                ", employeeMail='" + employeeMail + '\'' +
                ", employeeAddress='" + employeeAddress + '\'' +
                ", employeeStatus='" + employeeStatus + '\'' +
                '}';
    }
}
