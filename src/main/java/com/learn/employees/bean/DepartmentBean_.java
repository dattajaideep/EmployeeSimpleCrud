package com.learn.employees.bean;

import com.learn.employees.entity.DepartmentEntity;
import com.learn.employees.entity.EmployeeEntity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.Set;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(DepartmentEntity.class)
public class DepartmentBean_ {
    public static final String DEPARTMENTID = "departmentId";
    public static final String DEPARTMENTNAME = "departmentName";
    public static final String EMPLOYEESET = "employeeEntityList";
    public static volatile SingularAttribute<DepartmentEntity, Integer> departmentId;
    public static volatile SingularAttribute<DepartmentEntity, String> departmentName;
    public static volatile SetAttribute<DepartmentEntity, Set<EmployeeEntity>> employeeEntityList;
}
