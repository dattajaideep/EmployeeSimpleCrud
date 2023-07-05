package com.bajaj.employees.bean;

import lombok.*;

import java.util.Date;


@Getter
@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@ToString
@Builder
public class EmployeeBean {
    private int employeeId;
    private int employeeSalary;
    private Long employeeMobile;
    private String employeeName;
    private String employeeRole;
    private String employeeMail;
    private String employeeAddress;
    private String employeeStatus;
    private Date employeeCreatedDate;

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

    public Date getEmployeeCreatedDate() {
        return employeeCreatedDate;
    }

    public void setEmployeeCreatedDate(Date employeeCreatedDate) {
        this.employeeCreatedDate = employeeCreatedDate;
    }

    //Replaced Lombok(Plugin Issues)
    public EmployeeBean(int employeeId, int employeeSalary, Long employeeMobile, String employeeName, String employeeRole, String employeeMail, String employeeAddress, String employeeStatus, Date employeeCreatedDate) {
        this.employeeId = employeeId;
        this.employeeSalary = employeeSalary;
        this.employeeMobile = employeeMobile;
        this.employeeName = employeeName;
        this.employeeRole = employeeRole;
        this.employeeMail = employeeMail;
        this.employeeAddress = employeeAddress;
        this.employeeStatus = employeeStatus;
        this.employeeCreatedDate = employeeCreatedDate;
    }

    public EmployeeBean() {
    }

    @Override
    public String toString() {
        return "EmployeeBean{" +
                "employeeId=" + employeeId +
                ", employeeSalary=" + employeeSalary +
                ", employeeMobile=" + employeeMobile +
                ", employeeName='" + employeeName + '\'' +
                ", employeeRole='" + employeeRole + '\'' +
                ", employeeMail='" + employeeMail + '\'' +
                ", employeeAddress='" + employeeAddress + '\'' +
                ", employeeStatus='" + employeeStatus + '\'' +
                ", employeeCreatedDate=" + employeeCreatedDate +
                '}';
    }
}
