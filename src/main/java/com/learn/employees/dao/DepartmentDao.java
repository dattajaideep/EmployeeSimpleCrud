package com.learn.employees.dao;

import com.learn.employees.entity.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentDao extends JpaRepository<DepartmentEntity, Integer>, JpaSpecificationExecutor<DepartmentEntity> {
    Optional<DepartmentEntity> findByDepartmentNameIgnoreCase(@NonNull String departmentName);

    @Query("SELECT DISTINCT d FROM DepartmentEntity d JOIN d.employeeEntityList e WHERE upper(e.employeeStatus) =upper('active')")
    List<DepartmentEntity> findAllActive();

    @Query("SELECT DISTINCT d from DepartmentEntity d JOIN d.employeeEntityList e WHERE e.employeeId=:id")
    List<DepartmentEntity> findEmployeeDepartmentById(@Param("id") int id);
}
