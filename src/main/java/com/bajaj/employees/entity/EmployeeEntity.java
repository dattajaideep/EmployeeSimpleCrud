package com.bajaj.employees.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name = "employee_entity",uniqueConstraints = {@UniqueConstraint(columnNames = {"employeeId"})})
public class EmployeeEntity {
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
    @Column(name = "employeeCreatedDate")
    @Temporal(TemporalType.DATE)
    private Date employeeCreatedDate;

    public EmployeeEntity(int employeeSalary, long employeeMobile, String employeeName, String employeeRole) {
        this.employeeSalary=employeeSalary;
        this.employeeMobile=employeeMobile;
        this.employeeName=employeeName;
        this.employeeRole=employeeRole;
    }

    public EmployeeEntity(int employeeId, int employeeSalary, long employeeMobile, String employeeName, String employeeRole) {
        this.employeeId=employeeId;
        this.employeeSalary=employeeSalary;
        this.employeeMobile=employeeMobile;
        this.employeeName=employeeName;
        this.employeeRole=employeeRole;
    }


    @PrePersist
    protected void onCreate() {
        if (employeeStatus == null || employeeStatus.isEmpty()) {
            employeeStatus = "active";
        }
        employeeCreatedDate = new Date();
    }
}
