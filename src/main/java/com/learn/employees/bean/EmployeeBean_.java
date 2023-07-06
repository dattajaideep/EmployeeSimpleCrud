package com.learn.employees.bean;

import com.learn.employees.entity.EmployeeEntity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.Date;

/**
 * This class requried for
 */
@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(EmployeeEntity.class)
public class EmployeeBean_ {
    public static final String EMPLOYEE_ID = "employeeId";
    public static final String EMPLOYEE_SALARY = "employeeSalary";
    public static final String EMPLOYEE_MOBILE = "employeeMobile";
    public static final String EMPLOYEE_NAME = "employeeName";
    public static final String EMPLOYEE_ROLE = "employeeRole";
    public static final String EMPLOYEE_MAIL = "employeeMail";
    public static final String EMPLOYEE_ADDRESS = "employeeAddress";
    public static final String EMPLOYEE_STATUS = "employeeStatus";
    public static final String EMPLOYEE_CREATED_DATE = "employeeCreatedDate";
    public static volatile SingularAttribute<EmployeeEntity, Integer> employeeId;
    public static volatile SingularAttribute<EmployeeEntity, Integer> employeeSalary;
    public static volatile SingularAttribute<EmployeeEntity, Long> employeeMobile;
    public static volatile SingularAttribute<EmployeeEntity, String> employeeName;
    public static volatile SingularAttribute<EmployeeEntity, String> employeeRole;
    public static volatile SingularAttribute<EmployeeEntity, String> employeeMail;
    public static volatile SingularAttribute<EmployeeEntity, String> employeeAddress;
    public static volatile SingularAttribute<EmployeeEntity, String> employeeStatus;
    public static volatile SingularAttribute<EmployeeEntity, Date> employeeCreatedDate;
}
