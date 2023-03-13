package com.bajaj.employees.bean;

import lombok.*;

import java.util.Date;


@Getter
@Setter
@ToString
@NoArgsConstructor
@Builder
@AllArgsConstructor
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
}
