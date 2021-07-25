package com.avizhen.repository;

import com.avizhen.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query("SELECT e FROM Employee e WHERE e.firstName = :firstName AND e.lastName=:lastName")
    Employee findByName1AndName2(@Param("firstName") String firstName, @Param("lastName") String lastName);

}
