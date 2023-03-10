package com.bajaj.employees.entity;

import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name = "department_entity")
public class DepartmentEntity {
    @Id
    @Column(name = "department_id")
    private int departmentId;
    @Column(name = "department_name")
    private String departmentName;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "fk_dept_id",referencedColumnName = "department_id")
    private Set<EmployeeEntity> employeeEntityList;

    public DepartmentEntity(String departmentName) throws NullPointerException{
        if (departmentName == null) {
            throw new NullPointerException("Name cannot be null");
        }
        this.departmentName=departmentName;
    }

//    @Column(name ="manager_id")
//    private int managerId;
//    @Column(name = "manager_name")
//    private String managerName;

}
