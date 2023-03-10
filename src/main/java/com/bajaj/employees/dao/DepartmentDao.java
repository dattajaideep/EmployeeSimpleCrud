package com.bajaj.employees.dao;

import com.bajaj.employees.entity.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentDao extends JpaRepository<DepartmentEntity,Integer> {
    @Query("SELECT DISTINCT d FROM DepartmentEntity d JOIN d.employeeEntityList e WHERE e.employeeStatus ='active' and e.employeeStatus !='inactive'")
    List<DepartmentEntity> findAllActive();
}
